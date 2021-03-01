package stub.generation.pojo;
import java.util.*;
public class Methoder {
	private String signature;
	private Set<Dependency> dependencies;


	public Methoder(String signature){
		this.signature = signature;
	}

	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Set<Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Set<Dependency> dependencies) {
		this.dependencies = dependencies;
	}
}
