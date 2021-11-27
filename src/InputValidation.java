/**
 * Represents a class of static methods which facilitate input validation
 */
public class InputValidation {

  /**
   * Ensures that the given {@code input} is strictly greater than the given {@code lowerBound}
   *
   * @param input      The input to be checked
   * @param lowerBound The strict lower bound
   * @param message    The error message to be thrown if the input is not strictly greater than the
   *                   lower bound
   * @return The input if it is strictly greater than the lower bound
   * @throws IllegalArgumentException if the input is not strictly greater than the lower bound
   */
  public static int ensureGreaterThan(int input, int lowerBound, String message)
      throws IllegalArgumentException {
    if (input > lowerBound) {
      return input;
    } else {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Ensures that the given {@code input} is strictly less than the given {@code upperBound}
   *
   * @param input      The input to be checked
   * @param upperBound The strict upper bound
   * @param message    The error message to be thrown if the input is not strictly less than the
   *                   upperbound bound
   * @return The input if it is strictly less than the upper bound
   * @throws IllegalArgumentException if the input is not strictly less than the upper bound
   */
  public static int ensureLessThan(int input, int upperBound, String message)
      throws IllegalArgumentException {
    if (input < upperBound) {
      return input;
    } else {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Ensures that the given {@code input} is strictly greater than the {@code lowerBound} AND
   * strictly less than the {@code upperBound}
   *
   * @param input      The input to be checked
   * @param lowerBound The strict lower bound
   * @param upperBound The strict upper bound
   * @param message    The error message to be thrown if both conditions are not met
   * @return The input if both conditions are met
   * @throws IllegalArgumentException if the input is not strictly less than the upper bound or it
   *                                  is not strictly greater than the lower bound
   */
  public static int ensureWithin(int input, int lowerBound, int upperBound, String message)
      throws IllegalArgumentException {
    if (input > lowerBound && input < upperBound) {
      return input;
    } else {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Ensures that the given input is not null
   *
   * @param input The input to be checked
   * @param <X>   The type of the input
   * @return The input if it is not null
   * @throws IllegalArgumentException if the input is null
   */
  public static <X> X ensureNotNull(X input) throws IllegalArgumentException {
    if (input != null) {
      return input;
    } else {
      throw new IllegalArgumentException("Input must not be null");
    }
  }
}
