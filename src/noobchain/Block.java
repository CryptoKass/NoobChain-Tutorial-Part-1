package noobchain;

import java.util.Date;

public class Block {
	
	private String hash;
    private String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	//Block Constructor.  
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();

        //Making sure we do this after we set the other values.
		this.hash = calculateHash();
	}

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    //Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
        //Create a string with difficulty * "0"
	    String target = StringUtil.getDificultyString(difficulty);
		while (!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
	
}
