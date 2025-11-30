package org.example;

import org.example.Display.MenuPrincipal;
import org.example.Sistema.SistemaEscola;

public class Main {
    public static void main(String[] args) {

        // Inicializa todo o sistema (listas e admin master)
        SistemaEscola sistema = new SistemaEscola();

        // Cria o menu principal
        MenuPrincipal menu = new MenuPrincipal(sistema);

        // Executa o sistema
        menu.mostrar();
    }
}