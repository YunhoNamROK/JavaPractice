package channelBrowser;

import java.util.List;
import java.util.TreeMap;
import java.util.Random;

//Concrete Aggregate
public class ConcreteTV implements TV {
	TreeMap<Float, String> channels = new TreeMap<Float, String>();
	Random random = new Random();

	public ConcreteTV(List<String> c) {
	  for (int i=0; i < c.size(); i++){
	    channels.put(random.nextFloat(), c.get(i));
	  }
	}

	public ChannelIterator getIterator() {
		return new ConcreteChannelIterator(channels);
	}

}