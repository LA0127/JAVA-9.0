package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigInteger;

public class Controller {
    @FXML
    private TextField prime1Field;
    @FXML
    private TextField prime2Field;
    @FXML
    private Label nLabel;
    @FXML
    private Label phiLabel;
    @FXML
    private Label eLabel;
    @FXML
    private Label dLabel;
    @FXML
    private TextField messageField;
    @FXML
    private Label encryptedMessageLabel;
    @FXML
    private Label decryptedMessageLabel;

    private RSA rsa;

    @FXML
    public void generateKeys() {
        BigInteger prime1 = new BigInteger(prime1Field.getText());
        BigInteger prime2 = new BigInteger(prime2Field.getText());

        if (!RSA.isPrime(prime1) || !RSA.isPrime(prime2)) {
            // Muestra un mensaje de error o realiza alguna otra acción
            System.out.println("Ambos números deben ser primos.");
            return;
        }

        rsa = new RSA(prime1, prime2);
        nLabel.setText("n: " + rsa.getN().toString());
        phiLabel.setText("phi: " + rsa.getPhi().toString());
        eLabel.setText("e: " + rsa.getE().toString());
        dLabel.setText("d: " + rsa.getD().toString());
    }

    @FXML
    public void encrypt() {
        BigInteger message = new BigInteger(messageField.getText());
        BigInteger encryptedMessage = rsa.encrypt(message, rsa.getPublicKey()[0], rsa.getPublicKey()[1]);
        encryptedMessageLabel.setText("Mensaje cifrado: " + encryptedMessage.toString());
    }

    @FXML
    public void decrypt() {
        BigInteger encryptedMessage = new BigInteger(encryptedMessageLabel.getText().substring(17));  // quitar "Mensaje cifrado: "
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage, rsa.getPrivateKey()[0], rsa.getPrivateKey()[1]);
        decryptedMessageLabel.setText("Mensaje descifrado: " + decryptedMessage.toString());
    }
}
