package org.anb.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.anb.blockchain.util.HashUtil;

/**
 * Any blockchain consists of blocks interlinked with each other using hash.
 * To create a blockchain one must have below details in the Block:<br/>
 *
 * <ul>
 *     <li>Previous hash: This is the hash of the block just before this one.</li>
 *     <li>Block hash: This is the hash of the current block and is evaluated using the data of current block and
 *     hash of previous block.</li>
 *     <li>Data: This is a model where the data can be store. Any model depending on use-case. Can be a string,
 *     integer, a POJO anything serializable</li>
 * </ul>
 * <p>
 * This structure is at the core of any blockchain and ensure safety and security of blockchain.
 * Any tampering in the list will have a ripple effect across the list and this is then verified using the previous
 * and current hashes.
 * <br/><br/>
 * <p>
 * Further, since a blockchain is decentralized and is stored with each participant, any tampering of data by any
 * participant can be easily identified.
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Block {
    private String previousHash;
    private String blockHash;
    private Transaction[] transactions;

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public void generateBlockHash() {
        Object[] hash = new Object[]{transactions, previousHash};
        this.blockHash = HashUtil.getSHA256(hash);
    }
}
