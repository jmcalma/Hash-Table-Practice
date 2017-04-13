public class GenericArrayList<T>{

	private T[] data;
	private int size;

	public GenericArrayList(){
		data = (T[]) new Object[10];
		size = 0;
	}

	public GenericArrayList(int capacity){
		data = (T[]) new Object[capacity];
		size = 0;
	}

	private void increaseDataCapacity(){
		T[] newData = (T[]) new Object[data.length * 2];
		for(int i = 0; i < data.length; i++){
			newData[i] = data[i];
		}
		data = newData;
	}

	public void add(T value){
		if(size == data.length){
			increaseDataCapacity();
		}
		data[size] = value;
		size++;
	}

	public void add(int index, T value){
		if(index < size){
			if(size == data.length){
				increaseDataCapacity();
			}
			for(int i = size; i > index; i--){
				data[i] = data[i - 1];
			}
			data[index] = value;
			size++;
		} 
		else{
			throw new RuntimeException("Out of the list boundary.");
		}
	}

	public void clear(){
		size = 0;
	}

	public int indexOf(T value){
		for(int i = 0; i < size; i++){
			if(data[i] == value){
				return i;
			}
		}
		return -1;
	}

	public T get(int index){
		if(index < size){
			return data[index];
		} 
		else{
			throw new RuntimeException("Out of the list boundary.");
		}
	}

	public void remove(int index){
		if(index < size){
			for(int i = index; i < size - 1; i++){
				data[i] = data[i + 1];
			}
			size--;
		} 
		else{
			throw new RuntimeException("Out of the list boundary.");
		}
	}

	public void set(int index, T value){
		if(index < size){
			data[index] = value;
		}
		else{
			throw new RuntimeException("Out of the list boundary.");
		}
	}

	public int size(){
		return size;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for(int i = 0; i < size; i++){
			sb.append(data[i]);
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append(']').toString();
		return sb.toString();
	}
}