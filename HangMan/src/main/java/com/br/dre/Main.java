package com.br.dre;


import java.util.Scanner;
import java.util.stream.Stream;

import com.br.dre.model.HangmanChar;



public class Main {

    private final static  Scanner scanner = new Scanner(System.in);
    public static void main(String... args) {
        var characters = Stream.of(args)
        .map(letter-> letter.toLowerCase().charAt(0))
        .map(HangmanChar::new )
        .toList();
        System.out.println(characters);
        var hangmanGame =  new HangmanGame(characters);
        System.out.println("Bem vindo ao jogo da forca, tente adivinhar a palavra, boa sorte!");
        System.out.println(hangmanGame);
       
        var option = -1;
        while (true){
            System.out.println("Selecione uma das opções: ");
            System.out.println("1 - Informar uma letra");
            System.out.println("2 - Verificar Status do jogo");
            System.out.println("3 - sair do jogo");
            option  = scanner.nextInt();
            switch (option) {
               case 1 -> inputCharacter(hangmanGame);
               case 2 -> showGameStatus(hangmanGame);
               case 3 -> System.exit(0);
               default -> System.out.println("Opção Inválida!");
            }

            

            }
               
        }



    private static void showGameStatus(final HangmanGame hangmanGame) {
        System.out.println(hangmanGame.getHangmanGameStatus());
        System.out.println(hangmanGame);
    }

       
    
    private static void inputCharacter(HangmanGame hangmanGame) {
        System.out.println("Informe uma letra:");
        var character = scanner.next().charAt(0);
        try {
            hangmanGame.inputCharacter(character);
        }catch (LetterAlreadyInputedException ex){
            System.out.println(ex.getMessage());
            System.out.println(hangmanGame);
        } 
        
        catch (GameIsFinishedException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        System.out.println(hangmanGame);
    }
}