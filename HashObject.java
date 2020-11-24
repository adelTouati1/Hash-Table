
/**
 * @author adeltouati
 *
 * @param <T>
 */
public class HashObject <T> {
	
private int key;
private T object;
private int probCount;
private int probDup;

/**
 * @param key
 * @param s
 */
public HashObject(int key,T s){
	this.object=s;
	this.key=key;
	probCount=0;
	probDup=0;
}

/**
 * @return object
 */
public int getHash(){
	return object.hashCode();
}
public T getValue(){
	return object;

}

/**
 * @param probCount
 */
public void setProbCount(int probCount) {
	this.probCount=probCount;
}

/**
 * @return probCount
 */
public int probCount() {
	return probCount;
}
/**
 * @return probDup++
 */
public int probDupCount() {
	return probDup++;
}

/**
 * @return probDup
 */
public int probDup() {
	return probDup;
}

/**
 * @return key
 */
public int getKey(){
	return key;
}
	
public boolean Equal(T pass){
	if(pass.equals(object)){
		return true;
	}else{
		return false;
	}
}

public boolean equals(@SuppressWarnings("rawtypes") HashObject compareTo){
	return object.equals(compareTo.getValue());
}

public String toString(){
	return object.toString();
}


}
