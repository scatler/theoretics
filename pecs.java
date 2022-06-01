import java.util.ArrayList;
import java.util.List;

/*
аргумент метода: 	List<? extends Number>
реально пришло:		List<Double>
операция внутри:	list.get() = ? super Number

аргумент метода: 	List<? super Class3> = <Class3...Object>
реально пришло:		List<Class2>
операция внутри:	list.get() = только Object
операция внутри:	list.add() = только ? extends Class3
суть проблемы:		если бы можно было положить Class2,
                    то в случае если бы пришла коллекция Class3,
                    мы бы добавили менее специфичный объект ==>
                    ошибки в рантайме!
*/

public class Test {
    public static void main(String[] args) {
        List<? super Class3> l0 = new ArrayList<>();
        Object o = l0.get(0);
    }

    //Explicit arg type --> Class3
    public static void test1_superMethod() {
        superMethod(new ArrayList<>());
    }

    public static void test2_superMethod() {
        superMethod(new ArrayList<Object>());
    }

    public static void test3_superMethod() {
        superMethod(new ArrayList<Class2>());
    }

    public static void superMethod(List<? super Class3> list) {
        list.add(new Class3());
    }
}

class Class0 {}

class Class1 extends Class0 {}

class Class2 extends Class1 {}

class Class3 extends Class2 {}

class Class4 extends Class3 {}

class Class5 extends Class4 {}
