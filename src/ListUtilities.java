import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Represents a utilities class for the {@code List} interface
 */
public class ListUtilities {

  /**
   * Maps the given function over each element of the input list into a new list
   *
   * @param inputList The list to be mapped over
   * @param function  The function to be applied at each element of the input list
   * @param <X>       The type of the input list
   * @param <Y>       The type of the output list
   * @return The list with each element transformed by the given function
   * @throws IllegalArgumentException if the input list is null
   */
  public static <X, Y> List<Y> map(List<X> inputList, Function<X, Y> function)
      throws IllegalArgumentException {
    InputValidation.ensureNotNull(inputList);
    List<Y> outputList = new ArrayList<Y>(inputList.size());
    for (int index = 0; index < inputList.size(); index++) {
      outputList.add(function.apply(inputList.get(index)));
    }
    return outputList;
  }
}
