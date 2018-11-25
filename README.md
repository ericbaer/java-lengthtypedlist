# java-lengthtypedlist
An attempt at applying some of Haskell's techniques for simulating dependent types to Java. Lists of different lengths will have different types.

## Project structure

It consists of:

* Peano numbers: [`Zero`](src/lengthtypedlist/Zero.java) and [`S`](src/lengthtypedlist/S.java)
* Compile-time proof that one number is less than another: [`LessThan`](src/lengthtypedlist/LessThan.java), analogous to [`LTE` in Idris](https://github.com/idris-lang/Idris-dev/blob/master/libs/prelude/Prelude/Nat.idr#L116), and its subclasses [`ZeroLessThanN`](src/lengthtypedlist/ZeroLessThanN.java) (like `LTEZero`) and [`SuccLessThanSucc`](src/lengthtypedlist/SuccLessThanSucc.java) (like `LTESucc`). Used for compile-time checking of list indices.
* The actual list [`LengthTypedList`](src/lengthtypedlist/LengthTypedList.java), and its subclasses [`EmptyList`](src/lengthtypedlist/EmptyList.java) and [`NonEmptyList`](src/lengthtypedlist/NonEmptyList.java)

## Usage examples:

Making a list of zero `String`s:

```java
LengthTypedList<Zero, String> empty = new EmptyList<String>();
```
Adding a `String` to that to make a list of one `String`:

```java
LengthTypedList<S<Zero>, String> one =
        new NonEmptyList<Zero, String>("last element", empty);
```

And twice more:

```java
LengthTypedList<S<S<Zero>>, String> two =
        new NonEmptyList<S<Zero>, String>("second-to-last element", one);
LengthTypedList<S<S<S<Zero>>>, String> three =
        new NonEmptyList<S<S<Zero>>, String>("third-to-last element", two);
```

To get the second element from that last result, first we have to prove that two is less than three, by induction all the way down to proving that zero is less than one (we take that as an axiom). This is not quite a paragon of efficiency or code-conciseness, particularly for long lists, and could bear some improvement:

```java
LessThan<S<S<Zero>>, S<S<S<Zero>>>> twoLTthree = 
        new SuccLessThanSucc<S<Zero>, S<S<Zero>>>(
                // Proof that one is less than two
                new SuccLessThanSucc<Zero, S<Zero>>(
                        // Proof that zero is less than one
                        new ZeroLessThanN<Zero>()));
```

However, once we have that proof, we can use that it to get the second element:

```java
String lastElement = three.get(twoLTthree);
```

## Limitations

Since the `LessThan` class hierarchy is not extensible, it should be impossible to construct an invalid `LessThan` proof (e.g. `LessThan<S<S<Zero>>, S<Zero>>`), or to pass a proof of the wrong size to `get`.

However, there are probably still ways to do bad things like trying to `get` an element from a `LengthTypedList<Zero, ?>` by, for example, casting a properly-constructed proof to a bad type. Recall type erasure in Java generics: you wouldn't get a `ClassCastException` at the time of the bad cast. Instead you would get a `RuntimeException` from `get` when you tried to use the bad proof.
