package pojo;

import java.util.Set;

public class Dependency {
	private Set<Element> outputs;
	private Element input;

	public Dependency(Element input, Set<Element> outputs){
		this.input = input;
		this.outputs = outputs;
	}

	public Set<Element> getOutputs() {
		return outputs;
	}

	public void setOutputs(Set<Element> outputs) {
		this.outputs = outputs;
	}

	public Element getInput() {
		return input;
	}

	public void setInput(Element input) {
		this.input = input;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Dependency)) return false;

		Dependency that = (Dependency) o;

		if (outputs != null ? !outputs.equals(that.outputs) : that.outputs != null) return false;
		return input != null ? input.equals(that.input) : that.input == null;
	}

	@Override
	public int hashCode() {
		int result = outputs != null ? outputs.hashCode() : 0;
		result = 31 * result + (input != null ? input.hashCode() : 0);
		return result;
	}
}
