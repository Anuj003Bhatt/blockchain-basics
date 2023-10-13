package org.anb.blockchain.chain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.anb.blockchain.model.Block;
import org.anb.blockchain.util.HashUtil;

/**
 * This class represents a linked list representation of a blockchain.
 * Each node of this list is a block.
 *
 * @see Block
 */
@Getter
@Setter
public class LinkedChain {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChainNode {
        private Block block;
        private ChainNode next, previous;
    }

    ChainNode start, last;

    /**
     * To create a new blockchain every chain must have a starting node. This first block is given a special name
     * i.e., 'Genesis block'
     * This has a random hash with the previous hash being null. This is the only block
     * that can have a null value as previous hash.
     */
    public LinkedChain() {
        // The first block in a blockchain has a special name, i.e., 'Genesis block'
        Block genesis = new Block(
                null,
                HashUtil.getSHA256("Genesis101_Last"),
                null);
        start = last = new ChainNode(genesis, null, null);
    }

    /**
     * Append a Block to the end of the linked list
     * @param block: Block to add in chain
     */
    public void appendBlock(Block block) {
        ChainNode node = new ChainNode(block, null, null);
        if (start == null) {
            // No previous node, hence no previous hash
            start = last = node;
        } else {
            // last would be previous of new one
            node.getBlock().setPreviousHash(last.getBlock().getBlockHash());
            node.setPrevious(last);
            last.setNext(node);
            last = node;
        }
        node.getBlock().generateBlockHash();
    }

    /**
     * Just a utility function to print a linked list.
     */
    public void printChain() {
        ChainNode iterator = start;
        while (iterator != null) {
            System.out.println(iterator.getBlock());
            iterator = iterator.getNext();
        }
    }
}
