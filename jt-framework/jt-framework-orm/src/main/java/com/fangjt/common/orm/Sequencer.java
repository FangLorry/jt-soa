package com.fangjt.common.orm;

//sql过滤器
public class Sequencer {
	public enum SequencerDirectionEnum{asc,desc}
	private String key;
	private SequencerDirectionEnum sequencerDirection;
	
	public Sequencer(String key,SequencerDirectionEnum sequencerDirection){
		this.key=key;
		this.sequencerDirection = sequencerDirection;
	}
	
	public static Sequencer asc(String key){
		return new Sequencer(key, SequencerDirectionEnum.asc);
	}
	public static Sequencer desc(String key){
		return new Sequencer(key, SequencerDirectionEnum.desc);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SequencerDirectionEnum getSequencerDirection() {
		return sequencerDirection;
	}

	public void setSequencerDirection(SequencerDirectionEnum sequencerDirection) {
		this.sequencerDirection = sequencerDirection;
	}
	
	
	
}
