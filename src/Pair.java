/**
 * Represents a pair of objects
 * @param <X> The type of the first object
 * @param <Y> The type of the second object
 */
public class Pair<X, Y> {

  private final X first;
  private final Y second;

  /**
   * Constructs a new {@code Pair} object with the given pair of objects
   * @param first The first object in the pair
   * @param second The second object in the pair
   * @throws IllegalArgumentException if either object is null
   */
  public Pair(X first, Y second) throws IllegalArgumentException {
    this.first = InputValidation.ensureNotNull(first);
    this.second = InputValidation.ensureNotNull(second);
  }

  /**
   * Returns the first object in the pair
   * @return The first object in the pair
   */
  public X getFirst() {
    return this.first;
  }

  /**
   * Returns the second object in the pair
   * @return The second object in the pair
   */
  public Y getSecond() {
    return this.second;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", this.first, this.second);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else {
      if (!(other instanceof Pair<?, ?>)) {
        return false;
      }

      Pair<X, Y> typedOther = (Pair<X, Y>) other;
      return this.first.equals(typedOther.getFirst()) && this.second.equals(typedOther.getSecond());
    }
  }
}
