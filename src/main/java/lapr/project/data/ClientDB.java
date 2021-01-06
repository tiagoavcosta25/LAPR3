package lapr.project.data;

import lapr.project.model.Client;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDB extends DataHandler {
    
    
        public Client getClient(String id) {

            /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
             *
             * FUNCTION getSailor(id NUMBER) RETURN pkgSailors.ref_cursor
             * PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
             */
            CallableStatement callStmt = null;
            try {
                callStmt = getConnection().prepareCall("{ ? = call getSailor(?) }");

                // Regista o tipo de dados SQL para interpretar o resultado obtido.
                callStmt.registerOutParameter(1, OracleTypes.CURSOR);
                // Especifica o parâmetro de entrada da função "getSailor".
                callStmt.setLong(2, id);

                // Executa a invocação da função "getSailor".
                callStmt.execute();

                // Guarda o cursor retornado num objeto "ResultSet".
                ResultSet rSet = (ResultSet) callStmt.getObject(1);

                if (rSet.next()) {
                    long clientID = rSet.getLong(1);
                    String clientName = rSet.getString(2);

                    return new Client(clientID, clientName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new IllegalArgumentException("No Client with ID:" + id);
        }

        public void addClientToDB(Client client) {
            addClientToDB(client.getM_name(), client.getStrEmail(), client.get, client.getAge());
        }

        /**
         * Exemplo de invocação de uma "stored procedure".
         * <p>
         * Adiciona o marinheiro especificado à tabela "Sailors".
         *
         * @param sid    o identificador do marinheiro.
         * @param sname  o nome do marinheiro.
         * @param rating o "rating" do marinheiro.
         * @param age    a idade do marinheiro.
         */
        private void addClientToDB(long sid, String sname, long rating, long age) {
            try {
                openConnection();
                /*
                 *  Objeto "callStmt" para invocar o procedimento "addSailor" armazenado
                 *  na BD.
                 *
                 *  PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER)
                 *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
                 */
                CallableStatement callStmt = getConnection().prepareCall("{ call addSailor(?,?,?,?) }");

                callStmt.setLong(1, sid);
                callStmt.setString(2, sname);
                callStmt.setLong(3, rating);
                callStmt.setLong(4, age);

                callStmt.execute();

                closeAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * Exemplo de invocação de uma "stored procedure".
         * <p>
         * Remove o marinheiro especificado da tabela "Sailors".
         *
         * @param sid o identificador do marinheiro a remover.
         */
        public void removeSailor(long sid) {

            try {
                openConnection();
                /*
                 *  Objeto "callStmt" para invocar o procedimento "removeSailor"
                 *  armazenado na BD.
                 *
                 *  PROCEDURE removeSailor(sid NUMBER)
                 *  PACKAGE pkgSailors AS TYPE ref_cursor IS REF CURSOR; END pkgSailors;
                 */
                CallableStatement callStmt = getConnection().prepareCall("{ call removeSailor(?) }");

                callStmt.setLong(1, sid);

                callStmt.execute();

                closeAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

}
