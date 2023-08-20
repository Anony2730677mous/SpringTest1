package org.example;

import org.example.config.MyConfig;
import org.example.family.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);
        /*
        Шаг 1 - получение циклической зависимости при указании Autowired у сеттера, поля класса и конструктора
        На циклическую зависимость указывает исключение: UnsatisfiedDependencyException - Is there an unresolvable circular reference?
         */
        /*
        Шаг 2 - решение проблемы циклической зависимости с помощью:
         Вариант 1 - аннотации Lazy, применяемой к одному из конструкторов
        @Autowired
        @Lazy
        public Father(){}
         */
        /*
         Вариант 2 - применение аннотаций Autowired и PostConstruct в одном из классов-бинов.
         Применение метода init для создания зависимости от второго бина, применение конструкторов по умолчанию
            @PostConstruct
            public void init() {
            wife.setHusband(this);
    }
         */
        /*
         Вариант 3 - применение реализации интерфейсов ApplicationContextAware, InitializingBean
         в одном из бинов с целью получения из контекста экземпляра другого бина
         @Override
            public void afterPropertiesSet() throws Exception {
            wife = context.getBean(Mother.class);
    }

         @Override
            public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            context = applicationContext;
    }

         */
        /*
         Вариант 4 -  изменение структуры проекта таким образом, чтобы бины не имели циклической зависимости,
         например, созданием дополнительного класса, куда будут внедряться зависимые друг от друга бины
         */

        /*
        Шаг 3 - получение исключения NoUniqueBeanDefinitionException при создании бина SchoolMeet при инжекции
        бина типа Parent в бин типа SchoolMeet
         */
         /*
        Шаг 4 - решение проблемы неоднозначности опредления бина и исключения NoUniqueBeanDefinitionException с помощью:
         Вариант 1: аннотации @Primary, поставленной над классом одного из бинов
         */
        /*
         Вариант 2: аннотации Qualifier, поставленной над аргументом бина в конструкторе, в котором происходит исключение
            public SchoolMeet(@Qualifier("mother") Parent parent) {
            this.parent = parent;
    }
         */
         /*
        Шаг 5 - внедрение бина с областью видимости prototype в бин с областью видимости singleton
        Получение нового номера каждый раз при вызове конструктора бина Child.
        Использование аннотации Lookup в классе Mother для получения бина с областью видимости prototype в методе бина
        с областью видимости singleton
         */
        Parent mother = context.getBean(Mother.class);
        Parent father = context.getBean(Father.class);
        Child child1 = context.getBean(Child.class);
        Child child2 = context.getBean(Child.class);
        int childNumber1 = mother.getChildNumber(child1);
        int childNumber2 = father.getChildNumber(child2);
        System.out.println("Первый ребенок с номером: " + childNumber1);
        System.out.println("Второй ребенок с номером: " + childNumber2);
         /*
        Шаг 6 - внедрение бина с областью видимости prototype в бин с областью видимости singleton
        Использование аннотации Lookup для получения бина с областью видимости prototype в методе бина
        с областью видимости singleton
         */
        mother.parentSay(new Child().getChildNumber());
        /*
        Шаг 7 - создание собственной реализации BeanPostProcessor
        Класс FamilyBeanPostProcessor с аннотацией в бине Family
         */
        Family family = context.getBean(Family.class);
        family.sayRepeat();
        context.close();

    }
}