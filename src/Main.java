public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        int op;
        do {
            menu.ui();
            op = menu.lerOpcao("Escolha uma opção: ");
            menu.executarLogin(op);
        } while (op != 0);
    }

}
