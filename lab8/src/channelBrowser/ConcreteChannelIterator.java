package channelBrowser;

import java.util.NavigableSet;
import java.util.TreeMap;

//Concrete Iterator 
public class ConcreteChannelIterator implements ChannelIterator {

	private TreeMap<Float,String> channels;

	private Object[] keys;
	private int currentPos = 0;
	private int keysPos = 0;

	public ConcreteChannelIterator(TreeMap<Float,String> channels) {
		this.channels = channels;
		keys = channels.navigableKeySet().toArray();
	}

	public boolean hasNext() {
		return (currentPos < channels.size());
	}

	public String next() {
		String c = (String) channels.get(keys[keysPos]);
		keysPos++;
		currentPos++;
		return c;
	}

  public Object[] getKeys() {
    return keys;
  }

  public TreeMap<Float,String> getChannels() {
    return this.channels;
  }

}
