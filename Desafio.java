import java.sql.*;
import java.util.*;

public class Desafio {
    public static void main(String[] args) {
        String status = ("Nada aconteceu ainda.");
        boolean verdadeiroFalso = false;
        try{
            Connection conn = App.conectar();
            Scanner scnInput = new Scanner(System.in);
            System.out.println("Digite o login para verificação: ");
            String strLogin = scnInput.nextLine();
            System.out.println("Digite a senha para prosseguir: ");
            String strSenha = scnInput.nextLine();
            String strSqlSelect = "select * from `mysql_connector`.`tbl_login` where `login` = '" + strLogin + "' and `senha` = '" + strSenha + "';";
            Statement stmSql = conn.createStatement();
            ResultSet rsSql = stmSql.executeQuery(strSqlSelect);
            String login = "";
            String senha = "";
            while (rsSql.next()) {
                login += rsSql.getString("login");
                senha += rsSql.getString("senha");
            }
            if (strLogin.equals(login) || strSenha.equals(senha)){
                verdadeiroFalso = false;
                while (verdadeiroFalso == false){
                System.out.println("Bem vindo novamente, o que deseja alterar\n1 - Senha\n2 - Nome\n3 - Cancelar");
                Scanner scnAlterar = new Scanner(System.in);
                int intAlterar = scnAlterar.nextInt();

                switch (intAlterar) {
                    case 1:
                        System.out.println("Você selecionou mudar a senha.\nQual será sua nova senha?");
                        Scanner scnSenhaNova = new Scanner(System.in);
                        String strSenhaNova = scnSenhaNova.nextLine();
                        String stmSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `senha` = '" + strSenhaNova + "' WHERE (`login` = '" + strLogin + "')";
                        PreparedStatement preparedStm = conn.prepareStatement(stmSqlUpdate);
                        preparedStm.executeUpdate();
                        System.out.println("\nSenha alterada com sucesso para " + "[" + strSenhaNova + "]");
                        verdadeiroFalso = true;
                        System.out.println("Sessão encerrada.");
                        
                        break;
                
                    case 2:
                        System.out.println("Você selecionou mudar nome.\nQual será seu novo nome?");
                        Scanner scnNomeNovo = new Scanner(System.in);
                        String strNomeNovo = scnNomeNovo.nextLine();
                        String stmSqlUpdateDois = "UPDATE `mysql_connector`.`tbl_login` SET `nome` = '" + strNomeNovo + "' WHERE (`login` = '" + strLogin + "')";
                        PreparedStatement preparedStmDois = conn.prepareStatement(stmSqlUpdateDois);
                        preparedStmDois.executeUpdate();
                        System.out.println("\nNome alterado com sucesso para " + "[" + strNomeNovo + "]");
                        verdadeiroFalso = true;
                        System.out.println("Sessão encerrada.");
                        break;

                    case 3:
                        verdadeiroFalso = true;
                        System.out.println("Sessão encerrada.");

                }
            }

            }
            else{
                System.out.println("Login ou senha inválidos. Tente o login novamente");
            }
            stmSql.close();
            rsSql.close();
            scnInput.close();
          
        }
            
            catch ( Exception e ){
                System.out.println("Ops! erro." + e);
            }      
    }
    
}
