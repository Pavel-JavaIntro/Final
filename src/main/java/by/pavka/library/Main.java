package by.pavka.library;

public class Main {
  public static void main(String args[]) {
    StringBuilder template = new StringBuilder("ahahahaha");
    System.out.println(template.replace(template.length() - 1, template.length(), ")"));
  }

  static int factorial(int n) {
    // Факториал отрицательного числа не считается
    assert (n >= 0);

    System.out.println("N = " + n);
    // Если n превысит 10, то это может привести либо к целочисленному
    // переполнению результата, либо к переполнению стэка.
    assert (n <= 10);

    if (n < 2) {
      return 1;
    }

    return factorial(n - 1) * n;
  }
}
