
public class Link<E> {
	
	private boolean positive;
	private E connects;
	
	public Link(boolean positive, E connects) {
		this.positive = positive;
		this.connects = connects;
	}
	
	public boolean isPositive() {
		return positive;
	}
	
	public void setPositive(boolean pos) {
		positive = pos;
	}

	@Override
	public String toString() {
		return "{"+connects + " positive: " + positive+"}";
	}
	
}
