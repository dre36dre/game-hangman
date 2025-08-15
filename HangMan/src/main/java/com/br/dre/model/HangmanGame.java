package com.br.dre.model;

/*package dio.br.com.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import dio.br.com.exception.GameIsFinishedException;
import dio.br.com.exception.LetterAlreadyInputedException;
import static dio.br.com.model.HangmanGameStatus.LOSE;
import static dio.br.com.model.HangmanGameStatus.PENDING;
import static dio.br.com.model.HangmanGameStatus.WIN;

public final class HangmanGame {

    private final static int HANGMAN_INITIAL_LINE_LENGTH = 9;
    private final static int HANGMAN_INITIAL_LINE_LENGHT_WITH_LINE_SEPARATOR = 10;

    private final int lineSize;
    private final int hangmanInitialSize;
    private final LinkedList <HangmanChar> hangmanPaths;
    private final List<HangmanChar> characters;
    private final List<Character> failAttempts = new ArrayList<>();


    private String hangman;
    private HangmanGameStatus hangmanGameStatus;

    public HangmanGame(final List<HangmanChar> characters) {
        var whiteSpace = "".repeat(characters.size());
        var characterSpace = "-".repeat(characters.size());
        this.lineSize = HANGMAN_INITIAL_LINE_LENGHT_WITH_LINE_SEPARATOR + whiteSpace.length();
        this.hangmanGameStatus = PENDING;
        buildHangmanDesign(whiteSpace, characterSpace);
        this.characters = setCharacterSpacesPositionInGame(characters, whiteSpace.length());
        this.hangmanPaths= buildHangmanPathsPosition();
        this.hangmanInitialSize = hangman.length();
    }
   
    public void inputCharacter(final char character){
        if (this.hangmanGameStatus != PENDING){
            var message = this.hangmanGameStatus == WIN ?
                            "Parabéns, você ganhou!!!" :
                            "Você perdeu, tente novamente!";
            throw new GameIsFinishedException(message);

        }
          List<HangmanChar> found = (List<HangmanChar>) this.characters
          .stream()
          .filter(hc -> hc.getCharacter() == character).toList();

        if (found.isEmpty()){

             failAttempts.add(character);
             if (failAttempts.size() >= 6){
                this.hangmanGameStatus = LOSE;
             }
             
            rebuildHangman( this.hangmanPaths.removeFirst());

             return;
        } 

        if (found.get(0).isVisible()){
            throw new LetterAlreadyInputedException("A letra " + character + "já foi informada anteriormente!");
        }


        
        this.characters.forEach(HangmanChar -> {
            if (HangmanChar.getCharacter() == found.get(0).getCharacter()){

                HangmanChar.enableVisibility();
            }
        });
        if (this.characters.stream().noneMatch(HangmanChar :: isInvisible)){
            hangmanGameStatus  = WIN;
        }
        rebuildHangman(found.toArray(HangmanChar[]:: new ));
    }

    @Override
    public String toString() {
        return this.hangman;
    }

     private LinkedList<HangmanChar> buildHangmanPathsPosition() {
    final int larguraBase = HANGMAN_INITIAL_LINE_LENGTH; // 9
    final int saltoLinha = larguraBase + 1 + this.characters.size(); // +1 quebra, + espaço para letras
    final var HEAD_LINE = 3;
    final var BODY_LINE = 4;
    final var LEGS_LINE = 5;

    return new LinkedList<>(
        List.of(
            new HangmanChar('O',  HEAD_LINE * saltoLinha + 6),
            new HangmanChar('|',  BODY_LINE * saltoLinha + 6),
            new HangmanChar('/',  BODY_LINE * saltoLinha + 5),
            new HangmanChar('\\', BODY_LINE * saltoLinha + 7),
            new HangmanChar('/',  LEGS_LINE * saltoLinha + 5),
            new HangmanChar('\\', LEGS_LINE * saltoLinha + 7)
        )
    );
}

    
    /*private LinkedList<HangmanChar> buildHangmanPathsPosition(){

        final var HEAD_LINE = 3;
        final var BODY_LINE = 4;
        final var LEGS_LINE = 5;
        return new LinkedList<>(
            List.of(
                new HangmanChar('O' ,  this.lineSize * HEAD_LINE + 6),
                new HangmanChar('|' ,  this.lineSize * BODY_LINE + 6),
                new HangmanChar('/' ,  this.lineSize * BODY_LINE + 5),
                new HangmanChar('\\',  this.lineSize * BODY_LINE + 7),
                new HangmanChar('/' ,  this.lineSize * LEGS_LINE + 5),
                new HangmanChar('\\',  this.lineSize * LEGS_LINE + 7)
            )
        );
    }*/
    
    /*private List<HangmanChar> setCharacterSpacesPositionInGame(final List<HangmanChar> characters, final int whiteSpaceAmount){
        final var LINE_LETTER = 6;
        for (int i = 0; i < characters.size(); i++){
            characters.get(i).setPosition(this.lineSize * LINE_LETTER + HANGMAN_INITIAL_LINE_LENGTH + i);
        }
        return  characters;

    }

    private void rebuildHangman (final HangmanChar... hangmanChars){

        var hangmanBuilder = new StringBuilder(this.hangman);
        Stream.of(hangmanChars).forEach(
                  hc -> hangmanBuilder.setCharAt(hc.getPosition(), hc.getCharacter())
                  );

        
        var failMessage = this.failAttempts.isEmpty() ? "" : " Tentativas: " + this.failAttempts;
        this.hangman = hangmanBuilder.substring(0, hangmanInitialSize) + failMessage;

    }*/



    /*public void buildHangmanDesign(final String whiteSpace, final String characterSpace) {
    var baseLines = new StringBuilder[] {
        new StringBuilder("  -----"),
        new StringBuilder("  |   |"),
        new StringBuilder("  |   |"),
        new StringBuilder("  |"),
        new StringBuilder("  |"),
        new StringBuilder("  |"),
        new StringBuilder("  =========")
    };

    // Desenhar os membros da forca
    for (var hc : hangmanPaths) {
        int line = hc.getPosition() / lineSize;
        int col = hc.getPosition() % lineSize;
        baseLines[line].setCharAt(col, hc.getCharacter());
    }

    // Construir a linha da palavra após a base da forca
    StringBuilder wordLine = new StringBuilder("   "); // espaço inicial
    for (HangmanChar hc : characters) {
        wordLine.append(hc.isVisible() ? hc.getCharacter() : '_');
        wordLine.append(' ');
    }

    // Substituir a última linha pela base + palavra
    baseLines[6] = new StringBuilder(baseLines[6].toString() + "   " + wordLine.toString());

    // Imprimir
    for (var line : baseLines) {
        System.out.println(line);
    }
}*/


 


    /*private void buildHangmanDesign(final String whiteSpace, final String characterSpace){

        hangman = "  -----  " + whiteSpace + System.lineSeparator() + 
                  "  |   |  " + whiteSpace + System.lineSeparator() + 
                  "  |   |  " + whiteSpace + System.lineSeparator() + 
                  "  |      " + whiteSpace + System.lineSeparator() + 
                  "  |      " + whiteSpace + System.lineSeparator() + 
                  "  |      " + whiteSpace + System.lineSeparator() + 
                  "=========" + characterSpace + System.lineSeparator() ;

    }




    public static int getHangmanInitialLineLength() {
        return HANGMAN_INITIAL_LINE_LENGTH;
    }




    public static int getHangmanInitialLineLenghtWithLineSeparator() {
        return HANGMAN_INITIAL_LINE_LENGHT_WITH_LINE_SEPARATOR;
    }




    public int getLineSize() {
        return lineSize;
    }




    public int getHangmanInitialSize() {
        return hangmanInitialSize;
    }




    public LinkedList<HangmanChar> getHangmanPaths() {
        return hangmanPaths;
    }




    public List<HangmanChar> getCharacters() {
        return characters;
    }




    public List<Character> getFailAttempts() {
        return failAttempts;
    }




    public String getHangman() {
        return hangman;
    }




    public HangmanGameStatus getHangmanGameStatus() {
        return hangmanGameStatus;
    }
    

    

}*/

package dio.br.com.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import dio.br.com.exception.GameIsFinishedException;
import dio.br.com.exception.LetterAlreadyInputedException;
import static dio.br.com.model.HangmanGameStatus.LOSE;
import static dio.br.com.model.HangmanGameStatus.PENDING;
import static dio.br.com.model.HangmanGameStatus.WIN;

public final class HangmanGame {

    private final static int HANGMAN_INITIAL_LINE_LENGTH = 9;
    private final static int HANGMAN_INITIAL_LINE_LENGHT_WITH_LINE_SEPARATOR = 10; // mantive por compatibilidade

    private final int lineSize;
    private final int hangmanInitialSize;
    private final LinkedList<HangmanChar> hangmanPaths;
    private final List<HangmanChar> characters;
    private final List<Character> failAttempts = new ArrayList<>();

    private String hangman;
    private HangmanGameStatus hangmanGameStatus;

    public HangmanGame(final List<HangmanChar> characters) {
        var whiteSpace = "".repeat(characters.size());
        var characterSpace = "-".repeat(characters.size());
        this.hangmanGameStatus = PENDING;

        // 1) constrói o template para podermos medir o tamanho real das linhas (e do separador)
        buildHangmanDesign(whiteSpace, characterSpace);

        // 2) calcula o tamanho real da "linha completa" (linha + separador)
        int firstLineLength = hangman.indexOf(System.lineSeparator()); // comprimento da primeira linha (já inclui whiteSpace)
        this.lineSize = firstLineLength + System.lineSeparator().length();

        // 3) agora inicializamos os characters (posições dependem de lineSize)
        this.characters = setCharacterSpacesPositionInGame(characters, whiteSpace.length());

        // 4) depois criamos os caminhos do hangman (posições do boneco)
        this.hangmanPaths = buildHangmanPathsPosition();

        // 5) tamanho inicial do hangman (usado ao anexar mensagens de falha)
        this.hangmanInitialSize = hangman.length();
    }

    public void inputCharacter(final char character){
        if (this.hangmanGameStatus != PENDING){
            var message = this.hangmanGameStatus == WIN ?
                            "Parabéns, você ganhou!!!" :
                            "Você perdeu, tente novamente!";
            throw new GameIsFinishedException(message);
        }
        List<HangmanChar> found = (List<HangmanChar>) this.characters
          .stream()
          .filter(hc -> hc.getCharacter() == character).toList();

        if (found.isEmpty()){
             failAttempts.add(character);
             if (failAttempts.size() >= 6){
                this.hangmanGameStatus = LOSE;
             }
            rebuildHangman( this.hangmanPaths.removeFirst());
             return;
        }

        if (found.get(0).isVisible()){
            throw new LetterAlreadyInputedException("A letra " + character + " já foi informada anteriormente!");
        }

        this.characters.forEach(HangmanChar -> {
            if (HangmanChar.getCharacter() == found.get(0).getCharacter()){
                HangmanChar.enableVisibility();
            }
        });
        if (this.characters.stream().noneMatch(HangmanChar :: isInvisible)){
            hangmanGameStatus  = WIN;
        }
        rebuildHangman(found.toArray(HangmanChar[]:: new ));
    }

    
    @Override
    public String toString() {
        return this.hangman;
    }

    private LinkedList<HangmanChar> buildHangmanPathsPosition(){
        final var HEAD_LINE = 3;
        final var BODY_LINE = 4;
        final var LEGS_LINE = 5;
        return new LinkedList<>(
            List.of(
                new HangmanChar('O' ,  HEAD_LINE * this.lineSize + 6),
                new HangmanChar('|' ,  BODY_LINE * this.lineSize + 6),
                new HangmanChar('/' ,  BODY_LINE * this.lineSize + 5),
                new HangmanChar('\\',  BODY_LINE * this.lineSize + 7),
                new HangmanChar('/' ,  LEGS_LINE * this.lineSize + 5),
                new HangmanChar('\\',  LEGS_LINE * this.lineSize + 7)
            )
        );
    }

    private List<HangmanChar> setCharacterSpacesPositionInGame(final List<HangmanChar> characters, final int whiteSpaceAmount){
        final var LINE_LETTER = 6; // linha onde ficam os traços/espacos das letras
        for (int i = 0; i < characters.size(); i++){
            characters.get(i).setPosition(this.lineSize * LINE_LETTER + HANGMAN_INITIAL_LINE_LENGTH + i);
        }
        return characters;
    }

    private void rebuildHangman (final HangmanChar... hangmanChars){
        var hangmanBuilder = new StringBuilder(this.hangman);
        Stream.of(hangmanChars).forEach(
                  hc -> hangmanBuilder.setCharAt(hc.getPosition(), hc.getCharacter())
                  );

        var failMessage = this.failAttempts.isEmpty() ? "" : " Tentativas: " + this.failAttempts;
        this.hangman = hangmanBuilder.substring(0, hangmanInitialSize) + failMessage;
    }

    private void buildHangmanDesign(final String whiteSpace, final String characterSpace){
        hangman = "  -----  " + whiteSpace + System.lineSeparator() +
                  "  |   |  " + whiteSpace + System.lineSeparator() +
                  "  |   |  " + whiteSpace + System.lineSeparator() +
                  "  |      " + whiteSpace + System.lineSeparator() +
                  "  |      " + whiteSpace + System.lineSeparator() +
                  "  |      " + whiteSpace + System.lineSeparator() +
                  "=========" + characterSpace + System.lineSeparator();
    }

    // getters (mantive os seus)
    public static int getHangmanInitialLineLength() { return HANGMAN_INITIAL_LINE_LENGTH; }
    public static int getHangmanInitialLineLenghtWithLineSeparator() { return HANGMAN_INITIAL_LINE_LENGHT_WITH_LINE_SEPARATOR; }
    public int getLineSize() { return lineSize; }
    public int getHangmanInitialSize() { return hangmanInitialSize; }
    public LinkedList<HangmanChar> getHangmanPaths() { return hangmanPaths; }
    public List<HangmanChar> getCharacters() { return characters; }
    public List<Character> getFailAttempts() { return failAttempts; }
    public String getHangman() { return hangman; }
    public HangmanGameStatus getHangmanGameStatus() { return hangmanGameStatus; }
}

