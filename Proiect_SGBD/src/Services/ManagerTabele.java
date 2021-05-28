
package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

public class ManagerTabele {
    private static ManagerTabele managerTabele;
    private Conexiune conexiune;
    private String sql;
    private ArrayList<String> lista;
    
    private ManagerTabele() {
        conexiune = Conexiune.getInstance();
        lista = new ArrayList<>();
    }
    
    public static ManagerTabele getInstance() {
        if (managerTabele == null) {
            managerTabele = new ManagerTabele();
        }
        return managerTabele;
    }
    
    public String[] cautaTabeleFaraConstrangeriExistenta() {
        sql =   "SELECT table_name\n" +
                "FROM user_tables\n" +
                "MINUS\n" +
                "SELECT uc.table_name\n" +
                "FROM USER_CONSTRAINTS uc\n" +
                "WHERE uc.SEARCH_CONDITION_VC LIKE '%IS NOT NULL%'";
        
        lista.clear();
        
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            
            while (resultSet.next()) {
                lista.add(resultSet.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (lista.isEmpty()) {
            return null;
        }
        
        return lista.toArray(new String[0]);
    }
    
    public String[] cautaColoaneCareNuContinNull(String tabel) {
        cautaColoane(tabel);
        int size = lista.size();
        for (int i = size - 1; i >= 0; i--) {
            sql = "SELECT " + lista.get(i) + "\n" +
                  "FROM " + tabel + "\n" +
                  "WHERE " + lista.get(i) + " IS NULL AND ROWNUM = 1";
            
            try(Connection connection = conexiune.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);) {

                if (resultSet.next()) {
                    lista.remove(i);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (lista.isEmpty()) {
            return null;
        }
        return lista.toArray(new String[0]);
    }
    
    private String cautaColoanaPK(String tabel) {
        String coloanaPK = null;
        sql = "SELECT cols.column_name \n" +
              "FROM USER_constraints constr JOIN USER_CONS_COLUMNS cols ON constr.constraint_name = cols.constraint_name AND constr.owner = cols.owner\n" +
              "WHERE constr.constraint_type='P' AND constr.table_name = '" + tabel + "'";
        
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            
            if (resultSet.next()) {
                coloanaPK = resultSet.getString(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return coloanaPK;
    }
    
    private void cautaColoane(String tabel) {
        String coloanaPK = cautaColoanaPK(tabel);
        
        if (coloanaPK == null) {
            sql = "SELECT column_name " +
                  "FROM USER_TAB_COLUMNS " +
                  "WHERE table_name = '" + tabel + "'";
        } else {
            sql = "SELECT column_name " +
                  "FROM USER_TAB_COLUMNS " +
                  "WHERE table_name = '" + tabel + "' AND column_name <> '" + coloanaPK +"'";
        }
        
        lista.clear();
        
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            
            while (resultSet.next()) {
                lista.add(resultSet.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean adaugaConstrangere(String tabel, String coloana) {
        sql = "ALTER TABLE " + tabel + " MODIFY " + coloana + " NOT NULL";
        int val = -1;
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();) {
            
            val = statement.executeUpdate(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (val == 0) {
            return true;
        }
        return false;
    }
    //Urmatoarele 2 functii sunt folosite pentru afisarea datelor din tabele
    public String[] cautaToateTabelele() {
        sql =   "SELECT table_name\n" +
                "FROM user_tables\n";
        lista.clear();
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            
            while (resultSet.next()) {
                lista.add(resultSet.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (lista.isEmpty()) {
            return null;
        }
        
        return lista.toArray(new String[0]);
    }
    
    public String[] cautaToateColoanele(String tabel) {
        sql = "SELECT column_name " +
               "FROM USER_TAB_COLUMNS " +
               "WHERE table_name = '" + tabel + "'";
        lista.clear();
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            
            while (resultSet.next()) {
                lista.add(resultSet.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lista.isEmpty()) {
            return null;
        }
        return lista.toArray(new String[0]);
    }
    //Urmatoarele 4 Functii sunt pentru normalizare cheie semantica
    public String[] cautaTabeleFaraCheieSemantica() {
        sql =   "SELECT table_name\n" +
		"FROM USER_TABLES\n" +
		"MINUS\n" +				
		"SELECT table_name\n" +
		"FROM user_constraints uconstr\n" + 
		"WHERE uconstr.constraint_type = 'U'";
        lista.clear();
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            
            while (resultSet.next()) {
                lista.add(resultSet.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (lista.isEmpty()) {
            return null;
        }
        
        return lista.toArray(new String[0]);
    }
    
    public String[] cautaColoaneFaraDuplicate(String tabel) {
        cautaColoane(tabel);
        int size = lista.size();
        for (int i = size - 1; i >= 0; i--) {
            sql = "SELECT " + lista.get(i) + "\n" +
                    "FROM " + tabel + "\n" +
                    "GROUP BY " + lista.get(i) + "\n" +
                    "HAVING COUNT(" + lista.get(i) + ") > 1";
            
            try(Connection connection = conexiune.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);) {

                if (resultSet.next()) {
                    lista.remove(i);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (lista.isEmpty()) {
            return null;
        }
        return lista.toArray(new String[0]);
    }
    
    public boolean adaugaCheieSemantica(String tabel, String coloana) {
        sql = "ALTER TABLE " + tabel + " ADD UNIQUE ( " + coloana + ")";
        int val = -1;
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();) {
            
            val = statement.executeUpdate(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (val == 0) {
            return true;
        }
        return false;
    }
    
    public boolean adaugaCheieSemanticaCompusa(String tabel, String coloana1, String coloana2) {
        sql = "ALTER TABLE " + tabel + " ADD CONSTRAINT Unic_" + coloana1 + "_" + coloana2 +" UNIQUE ( " + coloana1 + "," + coloana2 + ")";
        int val = -1;
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();) {
            
            val = statement.executeUpdate(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (val == 0) {
            return true;
        }
        return false;
    }
    
    public String[] cautaTabeleFaraCheiePrimara() {
        sql =   "SELECT table_name\n" +
		"FROM USER_TABLES\n" +
		"MINUS\n" +				
		"SELECT table_name\n" +
		"FROM user_constraints uconstr\n" + 
		"WHERE uconstr.constraint_type = 'P'";
        lista.clear();
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            
            while (resultSet.next()) {
                lista.add(resultSet.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (lista.isEmpty()) {
            return null;
        }
        
        return lista.toArray(new String[0]);
    }
    
    public void adaugaCheiePrimara(String tabel) {
        sql = "ALTER TABLE " + tabel + " ADD (id_" + tabel + " NUMBER)";
        String sql1 = "CREATE SEQUENCE " + tabel + "_seq INCREMENT BY 1 START WITH 1 MAXVALUE 9999";
        String sql2 = "UPDATE " + tabel + " SET id_" + tabel +" = " + tabel + "_seq.NEXTVAL WHERE id_" + tabel + " IS NULL";
        String sql3 = "ALTER TABLE " + tabel + " MODIFY id_" + tabel +" NUMBER PRIMARY KEY";
        
        try(Connection connection = conexiune.getConnection();
            Statement statement = connection.createStatement();) {
            statement.addBatch(sql);//Adaugam cele 4 comenzi
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.addBatch(sql3);
            statement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
