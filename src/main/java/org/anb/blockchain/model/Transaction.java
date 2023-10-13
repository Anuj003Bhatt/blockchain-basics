package org.anb.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.anb.blockchain.util.HashUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction implements Serializable {
    private String to;
    private String from;
    private BigDecimal quantity;

    public String generateSHA256() throws NoSuchAlgorithmException {
        return HashUtil.getSHA256(this);
    }
}
