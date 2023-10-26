package application;

public class SimpleArrayList<T> implements SimpleList<T> {

  private T[] array;
  private int elements;

  public SimpleArrayList() {
    array = (T[]) new Object[64];
  }

  /**
   * Array Größe wird vergrößert oder verkleinert, je nach bedarf.
   *
   * @param requestedPosition
   */
  private void checkArraySize(int requestedPosition) {
    if (requestedPosition > array.length) {
      int newSize = array.length;
      if (array.length % 2 != 0) {
        newSize += (newSize + 1) / 2;
      } else {
        newSize += newSize / 2;
      }
      T[] biggerArray = (T[]) new Object[newSize];
      for (int i = 0; i < array.length; i++) {
        biggerArray[i] = array[i];
      }
    } else if (array.length > 64 || array.length >= elements * 2) {
      int newSize = 2 * array.length / 3;
      T[] smallerArray = (T[]) new Object[newSize];
      for (int i = 0; i < array.length; i++) {
        smallerArray[i] = array[i];
      }
    }
  }

  @Override
  public T extractNo(int requestedPosition) {
    if (requestedPosition >= array.length || array[requestedPosition] == null) {
      throw new IllegalArgumentException();
    }
    T temp = array[requestedPosition];
    array[requestedPosition] = null;
    for (int i = requestedPosition; i >= array.length; i++) {
      array[i] = array[i + 1];
    }
    elements--;
    checkArraySize(requestedPosition);
    return temp;
  }

  @Override
  public T getNo(int requestedPosition) {
    if (requestedPosition >= array.length || array[requestedPosition] == null) {
      throw new IllegalArgumentException();
    }
    return array[requestedPosition];
  }

  @Override
  public T setNo(int requestedPosition, T value) {
    if (requestedPosition >= array.length || array[requestedPosition] == null) {
      throw new IllegalArgumentException();
    }
    T temp = array[requestedPosition];
    array[requestedPosition] = value;
    return temp;
  }

  @Override
  public void putNo(int requestedPosition, T value) {
    if (requestedPosition > array.length || array[requestedPosition] != null) {
      throw new IllegalArgumentException();
    }
    checkArraySize(requestedPosition);
    array[requestedPosition] = value;
    elements++;
  }

  @Override
  public int getSize() {
    return array.length;
  }

  @Override
  public boolean isEmpty() {
    return elements == 0;
  }

  @Override
  public void clear() {
    array = (T[]) new Object[64];
    elements = 0;
  }

}
