package channelBrowser;

import java.util.List;
import java.util.TreeMap;
import java.util.Random;

//Concrete Aggregate
public class ConcreteTV implements TV {

    ConcreteChannelIterator i;
	TreeMap<Float, String> channels = new TreeMap<Float, String>();
	Random random = new Random();
	String[] iterater;

	public ConcreteTV(List<String> c) {
	  iterater = new String[c.size()];
	  for (int i=0; i < c.size(); i++){
	    channels.put(random.nextFloat(), c.get(i));
	  }
	  i = new ConcreteChannelIterator(channels);
	  iterate();
	}
	
	private void iterate(){
	  int iterCount = 0;
	  while (i.hasNext()){
	    iterater[iterCount] = i.next();
	    iterCount++;
	  }
	}

	public ChannelIterator getIterator() {
		return i;
	}
	
	public String[] getChannels(){
	  return iterater;
	}
	
	

}