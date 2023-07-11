package com.example;

import java.math.BigInteger;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;

    public RSA(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(65537); // Un valor comúnmente utilizado

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    public static boolean isPrime(BigInteger number) {
        return number.isProbablePrime(5);
    }

    public BigInteger[] getPublicKey() {
        return new BigInteger[]{e, N};
    }

    public BigInteger[] getPrivateKey() {
        return new BigInteger[]{d, N};
    }

    public BigInteger encrypt(BigInteger message, BigInteger e, BigInteger N) {
        return message.modPow(e, N);
    }

    public BigInteger decrypt(BigInteger cipher, BigInteger d, BigInteger N) {
        return cipher.modPow(d, N);
    }

    public BigInteger getPhi() {
        return phi;
    }

    public BigInteger getN() {
        return N;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
}
