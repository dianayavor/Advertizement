package main;

import model.Advert;
import model.Heading;
import model.Role;
import model.User;
import repository.AdvertRepository;
import repository.CrudRepository;
import repository.UserRepository;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Anna", "Barb", "anna@g.com", LocalDate.now(), true, Role.ADMIN, "");
        User user2 = new User("Viktoria", "Barb", "viktoriya@g.com", LocalDate.now(), true, Role.USER, "");
        User user3 = new User("Karla", "Barb", "karla@g.com", LocalDate.now(), true, Role.USER, "");
        User user4 = new User("Albina", "Barb", "albina@g.com", LocalDate.now(), true, Role.ANONIM, "");
        CrudRepository<User> userRepository = new UserRepository();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        System.out.println(userRepository.findAll());

        Advert advert1 = new Advert("StateFlow with One- and TwoWay-DataBinding on Android", "There has been a lot of talk in the Android community related to LiveData being deprecated in favor of StateFlow. This doesn’t seem to be the case but with the canary release of Android Studio Arctic Fox | 2020.3.1 (before the naming change it would have been 4.3) the last piece of moving away from LiveData is complete — DataBinding is enabled for StateFlow. From the initial glance the DataBinding works with StateFlow with minimum changes.\n" +
                "As the release notes state — For Kotlin apps that use coroutines, you can now use StateFlow objects as a data binding source to automatically notify the UI about changes in the data. Your data bindings will be lifecycle aware and will only be triggered when the UI is visible on the screen.\n" +
                "To use a StateFlow object with your binding class, you need to specify a lifecycle owner to define the scope of the StateFlow object, and in your layout, assign the properties and methods of your ViewModel component to the corresponding views using binding expressions, as shown in the following basic example:", user1, LocalDate.now(), LocalDate.now(), Heading.GAMES, true);
        Advert advert2 = new Advert("Top 10 Libraries every Java Developer should know", "Description2", user2, LocalDate.now(), LocalDate.now(), Heading.HAPPINESS, false);
        Advert advert3 = new Advert("Title 3", "Java is the number one programming language in Business Application development. It is also one of the top programming languages.\n" +
                "One of the key features of Java is that it has a feature-rich and vast Core library. While the Standard Java library is powerful, you will need other Java libraries in professional Software Development. With 25 years of active development and adoption in the industry and community, Java has many mature and useful libraries.\n" +
                "Here I am listing the top 10 Java libraries used in Java Applications in all domains. Whether you develop software for a hobby project or enterprise-grade project, you probably need most of the libraries I have listed below.\n" +
                "Apache Commons\n" +
                "Apache Commons is like a Swiss knife in Java software development and extends many of the Java Core libraries. If you ever feel like writing a utility class in your project, the chances are relatively high that there already exists a mature and powerful Apache Commons library. Apache Commons comprises 43 modular libraries covering domains like Collections, Math, Classes, Database, Caching, I/O Utils.\n" +
                "It is widely used in the industry and almost the unofficial Java Standard library enhancement. If you are working on a big project and not using any Apache Commons library, then you are probably re-inventing the wheel.\n" +
                "Main features:\n" +
                "Java Collections Framework extension.\n" +
                "Mathematics and Statistics components.\n" +
                "JDBC Helper.\n" +
                "Java Classes.\n" +
                "I/O Utilities.\n" +
                "Logging Utilities.", user1, LocalDate.now(), LocalDate.now(), Heading.PROGRAMING, true);
        Advert advert4 = new Advert("Publishing Android libraries to MavenCentral in 2021", "Creating a great library is hard work. Coming up with the idea, implementing it, making sure you have a nice, stable public API that you control carefully and maintain … That’s already lots to do.\n" +
                "After all that, you need to make your library available to the public. Technically, you could distribute the .aar file any way you want, but the norm is publishing it to a publicly available Maven repository. It's a good idea to use one of the well-established repositories that people are already likely to have in their projects, to make getting started with your library as easy as possible.\n" +
                "The simplest choice would be JitPack, which might not give you much in terms of customization or control, but is very easy to get started with. All you have to do is publish your project on GitHub, and JitPack should be able to build and distribute it immediately. If you’re new to libraries, this is a great choice for getting your code out there.\n" +
                "The next step up is Jcenter… Which is sunsetting in just 3 months from now, and is the reason for this updated article. Plus using it had its issues too.\n" +
                "Finally, the fanciest place you can be in is The Central Repository via Sonatype OSSRH (OSS Repository Hosting), which I’ll refer to as simply MavenCentral from here on. This is the place to be if you’re a Maven dependency. Artifacts on MavenCentral are well trusted, and their integrity can be verified, as they are all required to be signed by the author.\n" +
                "The publication process, however, and especially automating it, can be quite a headache. It’s easy to get stuck at many of the various steps no matter what tutorials you’re following, especially if they’re out of date, and this can get demotivating very quickly. It’s not uncommon to give up and just use Bintray/Jcenter instead — which is not a real option this year.\n" +
                "So, if you feel up for a bit of a challenge, and want to do things the right way, here’s how you can get a library into MavenCentral, at the start of 2021.", user1, LocalDate.now(), LocalDate.now(), Heading.GAMES, true);
        Advert advert5 = new Advert("How Dagger, Hilt and Koin differ under the hood?", "\"Dagger and Koin are without a doubt the two most popular dependency injection frameworks on Android. Both those libraries serve the same purpose and seem to be very similar but they both work quite differently under the hood.\\n\" +\n" +
                "                \"And what about Hilt? Hilt is a library that uses Dagger internally and just simplifies its usage, so everything I say here about Dagger is also applicable to Hilt.\\n\" +\n" +
                "                \"In this article, I won’t tell you which one of those libraries to choose. Instead, I want to show you how they are different under the hood and what might be the consequences of those differences for your app.\\n\" +\n" +
                "                \"Dagger\\n\" +\n" +
                "                \"If we want Dagger to provide an instance of some class, all we need to do is to add @Inject annotation to the constructor. Adding this annotation causes that Dagger will generate a Factory for this class at build time. In this case, since the class name is CompositeAdapter, it’ll generate a class named CompositeAdapter_Factory.\\n As you can see the factory implements get() method that returns a new instance of theCompositeAdapter class. This is actually a method specified in the Provider<T> interface that this class implements. Other classes can use Provider<T> interface to obtain an instance of a class.\" +\n" +
                "                \"This class contains all the information that is needed to create the instance of the CompositeAdapter class.\"", user3, LocalDate.now(), LocalDate.now(), Heading.HAPPINESS, false);
        Advert advert6 = new Advert("Sessionless Authentication using JWTs (with Node + Express + Passport JS)", "Authentication using stateful user sessions and session_ids stored in the cookie has been a strategy that has worked for decades. But with the rise of service oriented architectures and web services, there has been a push to design applications with the principle of statelessness in mind.\n" +
                "JWTs provide a stateless solution to authentication by removing the need to track session data on the server. Instead, JWTs allow us to safely and securely store our session data directly on the client in the form of a JWT. JWTs get a lot of criticism and skepticism, but the fact of the matter is that both session and JWT authentication have seen plenty of production usage and both implementations are secure and robust when it comes to handling user authentication. If statelessness is a practice you value in your system architecture, then JWTs are right for you. In this article, we will go over what JWTs are, the trade offs you make in choosing to use JWTs, and how you can implement them securely in your architecture.", user4, LocalDate.now(), LocalDate.now(), Heading.PROGRAMING, true);
        CrudRepository<Advert> advertRepository = new AdvertRepository();
        advertRepository.save(advert1);
        advertRepository.save(advert2);
        advertRepository.save(advert3);
        advertRepository.save(advert4);
        advertRepository.save(advert5);
        advertRepository.save(advert6);
        System.out.println(advertRepository.findAll());
    }
}
