package org.anb.blockchain;

import org.anb.blockchain.model.Block;
import org.anb.blockchain.chain.LinkedChain;
import org.anb.blockchain.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    /**
     * The developer can choose a variation to store the blockchain. This function represents a linked list sort
     * of representation.
     */
    private static void testLinkedChain() {
        LinkedChain chain = new LinkedChain();
        List.of(
                new Block(null,null, new Transaction[]{new Transaction("A", "B", BigDecimal.ONE)}),
                new Block(null,null, new Transaction[]{new Transaction("B", "C", BigDecimal.valueOf(2))}),
                new Block(null,null, new Transaction[]{new Transaction("C", "A", BigDecimal.valueOf(2))})
        ).forEach(chain::appendBlock);
        chain.printChain();
    }

    public static void main(String[] args) {
        testLinkedChain();
    }
}