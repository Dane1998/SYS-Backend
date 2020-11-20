/*
 * CA3
 */
package PackageFetch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.Callable;
import dto.CarDTO;
import dto.ChuckDTO;
import dto.DadDTO;
import dto.ForCarDTO;
import dto.ForMentorDTO;
import dto.MentorDTO;
import dto.PackageDTO;
import dto.TargetDTO;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import utils.HttpUtils;

/**
 *
 * @author magda
 */
public class DemoFetcher {

    private static String DEMO_CAR_URL = "https://reqres.in/api/car/1";
    private static String DEMO_CHUCK_URL = "https://api.chucknorris.io/jokes/random";
    private static String DEMO_DAD_URL = "https://icanhazdadjoke.com";
    private static String DEMO_MENTOR_URL = "http://dummy.restapiexample.com/api/v1/employee/1";

    public static PackageDTO returnPackage(Gson gson, ExecutorService threadPool) throws InterruptedException, ExecutionException, TimeoutException {
        Callable<CarDTO> carTask = new Callable<CarDTO>() {
            @Override
            public CarDTO call() throws IOException {
                String forCar = HttpUtils.fetchData(DEMO_CAR_URL);
                ForCarDTO forCarDTO = gson.fromJson(forCar, ForCarDTO.class);
                return forCarDTO.getCar();
            }
        };

        Future<CarDTO> futureCar = threadPool.submit(carTask);

        Callable<ChuckDTO> chuckTask = new Callable<ChuckDTO>() {
            @Override
            public ChuckDTO call() throws IOException {
                String chuck = HttpUtils.fetchData(DEMO_CHUCK_URL);
                ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);
                return chuckDTO;
            }
        };

        Future<ChuckDTO> futureChuck = threadPool.submit(chuckTask);

        Callable<DadDTO> dadTask = new Callable<DadDTO>() {
            @Override
            public DadDTO call() throws IOException {
                String dad = HttpUtils.fetchData(DEMO_DAD_URL);
                DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);
                return dadDTO;
            }
        };

        Future<DadDTO> futureDad = threadPool.submit(dadTask);

        Callable<MentorDTO> mentorTask = new Callable<MentorDTO>() {
            @Override
            public MentorDTO call() throws IOException {
                String forMentor = HttpUtils.fetchData(DEMO_MENTOR_URL);
                ForMentorDTO forMentorDTO = gson.fromJson(forMentor, ForMentorDTO.class);
                return forMentorDTO.getMentor();
            }
        };

        Future<MentorDTO> futureMentor = threadPool.submit(mentorTask);

        Callable<TargetDTO> targetTask = new Callable<TargetDTO>() {
            @Override
            public TargetDTO call() throws IOException {
                String target = HttpUtils.fetchData(DEMO_MENTOR_URL);
                TargetDTO targetDTO = gson.fromJson(target, TargetDTO.class);
                return targetDTO;
            }
        };

        Future<TargetDTO> futureTarget = threadPool.submit(targetTask);

        CarDTO car = futureCar.get(2, TimeUnit.SECONDS);
        ChuckDTO chuck = futureChuck.get(2, TimeUnit.SECONDS);
        DadDTO dad = futureDad.get(2, TimeUnit.SECONDS);
        MentorDTO mentor = futureMentor.get(2, TimeUnit.SECONDS);
        TargetDTO target = futureTarget.get(2, TimeUnit.SECONDS);

        return new PackageDTO(car, chuck, dad, mentor, target);
    }

}
