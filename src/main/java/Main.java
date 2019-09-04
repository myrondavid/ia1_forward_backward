import Database.Database;

public class Main {
    public static void main(String[] args) {
        new MainPanel();
        Database db = new Database();

        /* DESCOMENTAR PARA CRIAR O BANCO CASO NÃO EXISTA
        db.addRegras();
        db.addFatos();
        */
    }
}
