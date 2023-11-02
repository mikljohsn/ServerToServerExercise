package com.example.servertoserverexercise;

import com.example.servertoserverexercise.dto.AgeResponse;
import com.example.servertoserverexercise.dto.CountryResponse;
import com.example.servertoserverexercise.dto.FullResponse;
import com.example.servertoserverexercise.dto.GenderResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RemoteApiTester implements CommandLineRunner { //commandlinerunner laver en tråd som dens run metode kan køre på

    private Mono<String> callSlowEndpoint() {
        Mono<String> slowResponse = WebClient.create()
                .get()
                .uri("http://localhost:8080/random-string-slow")//the endpoint we want to call
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(e -> System.out.println("UUUPS : " + e.getMessage()));
        return slowResponse;
    }

    public void callEndpointBlocking(){
        long start = System.currentTimeMillis();
        List<String> randomStrings = new ArrayList<>(); //en liste til at indeholde vores responses

        Mono<String> slowResponse = callSlowEndpoint(); //variabel til at kalde vores slowendpoint
        randomStrings.add(slowResponse.block()); //Three seconds spent //tilføjer vores slowendpoint data gennem BLOCK

        slowResponse = callSlowEndpoint();
        randomStrings.add(slowResponse.block());//Three seconds spent

        slowResponse = callSlowEndpoint();
        randomStrings.add(slowResponse.block());//Three seconds spent
        long end = System.currentTimeMillis();
        randomStrings. add(0,"Time spent BLOCKING (ms): "+(end-start));

        //System.out.println(randomStrings.stream().collect(Collectors.joining(","))); //bruger streams til at joine vores string array til en enkelt string
        System.out.println(String.join(", ", randomStrings)); //bruger join direkte på String-klassen
    }
    public void callSlowEndpointNonBlocking(){
        long start = System.currentTimeMillis();
        Mono<String> sr1 = callSlowEndpoint();
        Mono<String> sr2 = callSlowEndpoint();
        Mono<String> sr3 = callSlowEndpoint();

        var response = Mono.zip(sr1,sr2,sr3).map(tuple3-> { //.zip samler hver mono-variabel til en mono, laver en tuple der kan indeholde 3 ting
            List<String> randomStrings = new ArrayList<>();
            randomStrings.add(tuple3.getT1());
            randomStrings.add(tuple3.getT2());
            randomStrings.add(tuple3.getT3());
            long end = System.currentTimeMillis();
            randomStrings.add(0,"Time spent NON-BLOCKING (ms): "+(end-start));
            return randomStrings;
        });
        List<String> randoms = response.block(); //We only block when all the three Mono's has fulfilled
        //System.out.println(randoms.stream().collect(Collectors.joining(",")));
        System.out.println(String.join(", ", randoms));
    }
  /*  Mono<GenderResponse> getGenderForName(String name) {
        WebClient client = WebClient.create();
        Mono<GenderResponse> gender = client.get()
                .uri("https://api.genderize.io?name="+name)
                .retrieve()
                .bodyToMono(GenderResponse.class);
        return gender;
    }*/
    List<String> names = Arrays.asList("lars", "peter", "sanne", "kim", "david", "maja");


    public void getGendersBlocking() {
        long start = System.currentTimeMillis();
        List<GenderResponse> genders = names.stream().map(name -> getGenderForName(name).block()).toList();
        long end = System.currentTimeMillis();
        System.out.println("Time for six external requests, BLOCKING: "+ (end-start));
    }

    public void getGendersNonBlocking() {
        long start = System.currentTimeMillis();
        var genders = names.stream().map(name -> getGenderForName(name)).toList();
        Flux<GenderResponse> flux = Flux.merge(Flux.concat(genders));
        List<GenderResponse> res = flux.collectList().block();
        long end = System.currentTimeMillis();
        System.out.println("Time for six external requests, NON-BLOCKING: "+ (end-start));
    }
    //PART 2
    Mono<GenderResponse> getGenderForName(String name) { //kopieret fra tidligere
        WebClient client = WebClient.create();
        Mono<GenderResponse> gender = client.get()
                .uri("https://api.genderize.io?name="+name)
                .retrieve()
                .bodyToMono(GenderResponse.class);
        return gender;
    }
    Mono<AgeResponse> getAgeInfoForName(String name) {
        WebClient client = WebClient.create();
        Mono<AgeResponse> ageInfo = client.get()
                .uri("https://api.agify.io/?name=" + name)
                .retrieve()
                .bodyToMono(AgeResponse.class);
        return ageInfo;
    }
    Mono<CountryResponse> getCountryInfoForName(String name) {
        WebClient client = WebClient.create();
        Mono<CountryResponse> countryInfo = client.get()
                .uri("https://api.nationalize.io/?name=" + name)
                .retrieve()
                .bodyToMono(CountryResponse.class);
        return countryInfo;
    }
    public Mono<FullResponse> getAllInfo(String name){
       Mono<GenderResponse> gender = getGenderForName(name);
       Mono<AgeResponse> age = getAgeInfoForName(name);
       Mono<CountryResponse> nationality = getCountryInfoForName(name);
       return Mono.zip(gender,age,nationality).map(tuple -> new FullResponse(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }



    @Override
    public void run(String... args) throws Exception {

        //System.out.println(callSlowEndpoint().block()); //HUSK at bruge block, ellers får vi ikke værdierne returneret. Ellers står de og "venter"

        //callEndpointBlocking();

        //callSlowEndpointNonBlocking();

        //Mono<Gender> response = getGenderForName("mikkel");

        //var response = getGenderForName("mikkel"); //der kan bruges var fordi at compileren kan se returtypen i den kaldte metode.
        //System.out.println(response.block().getGender());

        //getGendersBlocking();

        //getGendersNonBlocking();

        //var response = getAgeInfoForName("mikkel");
        //System.out.println(response.block().toString());

        //var response = getCountryInfoForName("Mikkel");
        //System.out.println(response.block().toString());
        System.out.println(getAllInfo("mikkel").block().toString());
    }
}
