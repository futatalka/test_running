package com.example.demo;
import com.example.demo.Services.MasterService;
import com.example.demo.model.Master;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import info.debatty.java.stringsimilarity.Jaccard;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;


final class ListasFin {

    private final List<String> modTexto;
    private final List<Date> fechaTweet;
    private final List<Long> statusTweet;
    private final List<Long> idCuenta;

    private final List<String> urlTweet;
    private final List<String> urlText;

    private final List<String> urlImage;

    public ListasFin(List<String> modTexto, List<Date> fechaTweet, List<Long> idCuenta, List<Long> statusTweet, List<String> urlTweet, List<String> urlText, List<String> urlImage) {

        this.modTexto = modTexto;
        this.fechaTweet = fechaTweet;
        this.statusTweet = statusTweet;
        this.idCuenta = idCuenta;
        this.urlTweet = urlTweet;
        this.urlImage = urlImage;
        this.urlText = urlText;

    }

    public List<String> getModTexto() {
        return modTexto;
    }

    public List<Date> getFechaTweet() {
        return fechaTweet;
    }

    public List<Long> getIdCuenta() {
        return idCuenta;
    }

    public List<Long> getStatusTweet() {
        return statusTweet;
    }

    public List<String> getUrlTweet() {
        return urlTweet;
    }

    public List<String> getUrlImage() {
        return urlImage;
    }

    public List<String> getUrlText() {
        return urlText;
    }


}
final class Unicos {

    public static LinkedHashMultimap<String, String> fileData;
    public static LinkedHashMultimap<String, String> fileUrlD;
    public static LinkedHashMultimap<String, String> fileDateD;
    public static LinkedHashMultimap<String, String> fileImageD;
    public static LinkedHashMultimap<String, String> fileTextD;

    public Unicos(LinkedHashMultimap<String, String> fileData, LinkedHashMultimap<String, String> fileUrlD, LinkedHashMultimap<String, String> fileDateD,
                  LinkedHashMultimap<String, String> fileImageD, LinkedHashMultimap<String, String> fileTextD) {
        Unicos.fileData = fileData;
        Unicos.fileUrlD = fileUrlD;
        Unicos.fileDateD = fileDateD;
        Unicos.fileImageD = fileImageD;
        Unicos.fileTextD = fileTextD;

    }

    public LinkedHashMultimap<String, String> getFileData() {
        return fileData;
    }

    public LinkedHashMultimap<String, String> getFileUrlD() {
        return fileUrlD;
    }

    public LinkedHashMultimap<String, String> getFileDateD() {
        return fileDateD;
    }

    public LinkedHashMultimap<String, String> getFileImageD() {
        return fileImageD;
    }

    public LinkedHashMultimap<String, String> getFileTextD() {
        return fileTextD;
    }
}
final class Maps {
    public static LinkedHashMultimap<String, String> jaccardResultsFinal;


    public Maps(LinkedHashMultimap<String, String> jaccardResultsFinal) {
        Maps.jaccardResultsFinal = jaccardResultsFinal;

    }

    public LinkedHashMultimap<String, String> getJaccardResultsFinal() {
        return jaccardResultsFinal;
    }

}
class Serie {
    @SerializedName("master")
    @Expose
    private String master;
    @SerializedName("info")
    @Expose
    private List<Info> info;

    /**
     *
     */
    public Serie(String master, List<Info> info) {
        super();
        this.master = master;
        this.info = info;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Serie withMaster(String master) {
        this.master = master;
        return this;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public Serie withInfo(List<Info> info) {
        this.info = info;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Serie.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("master");
        sb.append('=');
        sb.append(((this.master == null) ? "<null>" : this.master));
        sb.append(',');
        sb.append("info");
        sb.append('=');
        sb.append(((this.info == null) ? "<null>" : this.info));
        sb.append(',');

        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }

        return sb.toString();
    }

}
class Info implements Serializable {

    @SerializedName("iden")
    @Expose
    private String iden;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("urlImage")
    @Expose
    private String urlImage;

    @SerializedName("urlText")
    @Expose
    private String urlText;


    /**
     * No args constructor for use in serialization
     */
    public Info() {
    }


    public Info(String iden, String status, String date, String link, String urlImage, String urlText) {
        super();
        this.iden = iden;
        this.status = status;
        this.date = date;
        this.link = link;
        this.urlImage = urlImage;
        this.urlText = urlText;
    }

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public Info withIden(String iden) {
        this.iden = iden;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Info withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Info withDate(String date) {
        this.date = date;
        return this;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Info withLink(String link) {
        this.link = link;
        return this;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Info withUrlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public String getUrlText() {
        return urlText;
    }

    public void setUrlText(String urlText) {
        this.urlText = urlText;
    }

    public Info withUrlText(String urlText) {
        this.urlText = urlText;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Info.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("iden");
        sb.append('=');
        sb.append(((this.iden == null) ? "<null>" : this.iden));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null) ? "<null>" : this.status));
        sb.append(',');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null) ? "<null>" : this.date));
        sb.append(',');
        sb.append("link");
        sb.append('=');
        sb.append(((this.link == null) ? "<null>" : this.link));
        sb.append(',');
        sb.append("urlImage");
        sb.append('=');
        sb.append(((this.urlImage == null) ? "<null>" : this.urlImage));
        sb.append(',');
        sb.append("urlText");
        sb.append('=');
        sb.append(((this.urlText == null) ? "<null>" : this.urlText));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}



@Configuration
@EnableScheduling


public class ScheduledTask extends TimerTask {
    @Autowired
    private MasterService masterService;



    static String directorio = "src/main/resources/json/";
    static String newLine = System.getProperty("line.separator");
    private static final LinkedHashSet<Serie> newsJson = new LinkedHashSet<>();

    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            //.registerTypeAdapter(LinkedHashSet<Serie>.class, new APICallerTask.LinkedHashMultiMapAdapter<String, Serie>())
            .create();

    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }

    private static class Constantes {
        private static final long[] usuario = {7668952, 201493641, 14638581, 90227660, 18248645, 51326671, 24952459, 58048133};//cooperativa,adnradio,biobio,24horastvn,cnnchile,CHV,T13,Mega
        private static final String[] merma = {"lapruebadeadn", "cnnprime", "chvnoticias", "cooperativacontigo", "futuro_360", "t13central", "24horascentral", "su", "le", "al", "es", "de", "que", "a", "ante", "bajo", "con", "contra", "del", "desde", "durante", "en", "entre", "hacia", "hasta", "mediante", "para", "por", "segun", "sin", "sobre", "tras", "via", "el", "la", "los", "las", "un", "una", "unos", "una", "y", "o"};
        private static final String[] direccion = {"www.chvnoticias.cl", "www.tvn.cl", "www.24horas.cl", "www.biobiochile.cl", "www.cooperativa.cl", "alairelibre.cl/", "futuro360.com/", "www.adnradio.cl", "www.t13.cl", "www.meganoticias.cl", "www.cnnchile.com"};
        // private static final String[] direccion2 = {"www.twitch.tv/megadeportescl","cooperativapodcast.cl","supergeek.cl","open.spotify.com","youtu.be/", "www.youtube.com","www.tvnplay.cl", "pluto.tv/", "www.pluto.tv", "www.facebook.com", "bit.ly", "spotify.com", " podcasts.apple.com/", "instagram.com", "twitter.com", "fb.com", "fb.me", "tiktok.com", "vm.tiktok.com"};

    }

    static int counter = 0;
    Timer timer;
    static final DateTimeFormatter INSTANT_FORMATTER
            = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of("Chile/Continental"));
    static final DateTimeFormatter INSTANT_FORMATTER2
            = DateTimeFormatter.ofPattern("yyyy-MM").withZone(ZoneId.of("Chile/Continental"));
    static final DateTimeFormatter INSTANT_FORMATTER3
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Chile/Continental"));
    AtomicReference<Instant> moment = new AtomicReference<>(Instant.now());

    private static twitter4j.conf.Configuration setupConfigurationBuilder() {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey("69xer3xOo3kNPSesSRe3auv10");
        cb.setOAuthConsumerSecret("JlHfoNIspBq5bkrMaRM9QhVSa5WYyh0GYDlWsYRNoGCjAORcuL");
        cb.setOAuthAccessToken("1491960763321401347-CqCMV0ZhrCsT2wr2Pxt1dAWraLUVh7");
        cb.setOAuthAccessTokenSecret("AAnchxuMmCDXGZpERyIVhfxvqTfTx9QgfZ0q2cRddk6KL");
        cb.setTweetModeExtended(true);
        cb.setHttpStreamingReadTimeout(300000);//300000
        cb.setHttpConnectionTimeout(200000);//20000
        cb.setHttpReadTimeout(120000);//120000
        cb.setHttpRetryCount(2);
        cb.setHttpRetryIntervalSeconds(5);
        cb.setPrettyDebugEnabled(false);

        return cb.build();
    }
    public static void Archivo() {

        FileWriter fileWriter = null;
        FileWriter fileUrlsUnique = null;
        FileWriter fileWriterDate = null;
        FileWriter fileUrlImage = null;
        FileWriter fileUrlText = null;

        File fileUnicos = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "unicos " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");


        try {

            fileWriter = new FileWriter(fileUnicos.getAbsoluteFile(), false);


        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileWriter != null;
        BufferedWriter bw = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bw);


        File fileData = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "data " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");

        FileReader fR;
        try {
            fR = new FileReader(fileData.getAbsoluteFile());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        BufferedReader bR;
        try {

            LinkedHashSet<String> set = new LinkedHashSet<>();


            bR = new BufferedReader(fR);


            String lineFBr;

            while ((lineFBr = bR.readLine()) != null) {
                String[] splited = lineFBr.split("-");

                for (int i = 0; i < splited.length; i++) {

                    if (!set.contains(lineFBr)) {
                        set.add(lineFBr);
                        printWriter.print(lineFBr + newLine);

                    }
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        printWriter.close();

    }
/*
    public void SaveData(){

        ObjectMapper mapper = new ObjectMapper();

        TypeReference<List<Master>> typeReference = new TypeReference<List<Master>>(){};

        InputStream inputStream = MasterList.class.getResourceAsStream("/json/Noticias.json");


        try {

            List<Master> users = mapper.readValue(inputStream,typeReference);
            System.out.println(users);
            masterService.save(users);
            System.out.println("Users Saved!");


        } catch (IOException e){
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }
*/
    public static Unicos reading() {

        LinkedHashMultimap<String, String> fileData = LinkedHashMultimap.create();
        LinkedHashMultimap<String, String> fileUrlD = LinkedHashMultimap.create();
        LinkedHashMultimap<String, String> fileDateD = LinkedHashMultimap.create();
        LinkedHashMultimap<String, String> fileImageD = LinkedHashMultimap.create();
        LinkedHashMultimap<String, String> fileTextD = LinkedHashMultimap.create();

        File readData = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "data " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
        File readUrlD = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "urls " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
        File readDateD = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "date " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
        File readImageD = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "urlsImage " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
        File readTextD = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "urlsText " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");


        FileReader fRData, fRUrlD, fRDateD, fRImageD, fRTextD;
        try {
            fRData = new FileReader(readData.getAbsoluteFile());
            fRUrlD = new FileReader(readUrlD.getAbsoluteFile());
            fRDateD = new FileReader(readDateD.getAbsoluteFile());
            fRImageD = new FileReader(readImageD.getAbsoluteFile());
            fRTextD = new FileReader(readTextD.getAbsoluteFile());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedReader bRData, bRUrlD, bRDateD, bRImageD, bRTextD;
        try {

            LinkedHashSet<String> setData = new LinkedHashSet<>();
            LinkedHashSet<String> setUrlD = new LinkedHashSet<>();
            LinkedHashSet<String> setDateD = new LinkedHashSet<>();
            LinkedHashSet<String> setImageD = new LinkedHashSet<>();
            LinkedHashSet<String> setTextD = new LinkedHashSet<>();

            bRData = new BufferedReader(fRData);
            bRUrlD = new BufferedReader(fRUrlD);
            bRDateD = new BufferedReader(fRDateD);
            bRImageD = new BufferedReader(fRImageD);
            bRTextD = new BufferedReader(fRTextD);

            String lineFBr, lineFUrl, lineFDate, lineFUrlI, lineFUrlT;

            while ((lineFBr = bRData.readLine()) != null) {
                String[] splited = lineFBr.split("-");

                for (int i = 0; i < splited.length; i++) {

                    if (!setData.contains(lineFBr)) {
                        setData.add(lineFBr);
                        fileData.put(splited[0], splited[1]);

                    }
                }


            }

            while ((lineFUrl = bRUrlD.readLine()) != null) {
                String[] splited = lineFUrl.split("#");
                for (int i = 0; i < splited.length; i++) {
                    if (!setUrlD.contains(splited[0]) && !setUrlD.contains(splited[1])) {// Para evitar que los URL se repitan con distintos status
                        setUrlD.add(splited[0]);
                        fileUrlD.put(splited[0], splited[1]);

                    }
                }

            }

            while ((lineFDate = bRDateD.readLine()) != null) {
                String[] splited = lineFDate.split("-");

                for (int i = 0; i < splited.length; i++) {

                    if (!setDateD.contains(splited[0])) {

                        setDateD.add(splited[0]);
                        fileDateD.put(splited[0], splited[1]);

                    }
                }

            }

            while ((lineFUrlI = bRImageD.readLine()) != null) {
                String[] splited = lineFUrlI.split("#");

                for (int i = 0; i < splited.length; i++) {

                    if (!setImageD.contains(splited[0]) && !setImageD.contains(splited[1])) { //EVITAR QUE HAYA MISMAS IMAGENES CON DISTINTOS STATUS
                        setImageD.add(splited[0]);
                        fileImageD.put(splited[0], splited[1]);

                    }
                }

            }
            while ((lineFUrlT = bRTextD.readLine()) != null) {


                String[] splited = lineFUrlT.split("Æ");

                for (int i = 0; i < splited.length; i++) {

                    if (!setTextD.contains(splited[0]) && !setTextD.contains(splited[1])) {// Para que no haya mismo texto con distintos status
                        setTextD.add(splited[0]);

                        fileTextD.put(splited[0], splited[1]);

                    }

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Unicos(fileData, fileUrlD, fileDateD, fileImageD, fileTextD);
    }
    @SneakyThrows
    public static ListasFin resultados() {


        Twitter twitter = new TwitterFactory(setupConfigurationBuilder()).getInstance();

        try (WebClient webClient = new WebClient()) {
            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setTimeout(3 * 1000);
        }

        List<String> tweetCuenta = new ArrayList<>(); // Obtiene el nombreCuenta del usuario, cuando fue creado el tweet y el cuerpo del tweet
        List<String> modTexto = new ArrayList<>();
        List<String> linkTweet = new ArrayList<>();
        List<Date> fechaTweet = new ArrayList<>();
        List<Long> idCuenta = new ArrayList<>();
        List<Long> statusTweet = new ArrayList<>();
        List<String> urlTweet = new ArrayList<>();
        List<String> urlText = new ArrayList<>();
        List<String> urlImage = new ArrayList<>();

        LinkedHashSet<Long> prueba = new LinkedHashSet<>();

        for (long value : Constantes.usuario) {
            ResponseList<Status> userInfo = null;
            try {
                Paging p = new Paging();
                p.setCount(3);
                userInfo = twitter.getUserTimeline(value, p);

            } catch (TwitterException ex) {

                java.util.logging.Logger.getLogger(ScheduledTask.class.getName()).log(Level.FINE,null,ex);

            }

            assert userInfo != null;

            for (Status s : userInfo) {
                boolean isRetweet = s.getRetweetedStatus() != null;
                String textoTweet;
                URLEntity[] urls;

                if (isRetweet) {
                    textoTweet = s.getRetweetedStatus().getText().trim();
                    urls = s.getRetweetedStatus().getURLEntities();
                } else {
                    textoTweet = s.getText().trim();
                    urls = s.getURLEntities();
                }

                idCuenta.add(s.getUser().getId());

                tweetCuenta.add(s.getUser().getName() + " " + s.getCreatedAt() + " " + textoTweet);// Obtiene el nombreCuenta del usuario, cuando fue creado el tweet y el cuerpo del tweet
                String url = "https://twitter.com/" + s.getUser().getScreenName() + "/status/" + s.getId();
                linkTweet.add(url);
                fechaTweet.add(s.getCreatedAt());
                statusTweet.add(s.getId());
                modTexto.add(textoTweet.toLowerCase().replaceAll("[^\\wñ,. \\u00C0-\\u00FF]", ""));//Obtiene el cuerpo del tweet y pasa todas las letras a minusculas, como también reemplaza todos los caracteres especiales con excepción de la ñ y las vocales con caracteres especiales.

                if (isRetweet) {
                    urlTweet.add("https://twitter.com/" + s.getUser().getScreenName() + "/status/" + s.getId());
                    textoTweet = textoTweet.split("https")[0];
                    System.out.println("textotweet1 " + textoTweet);
                    urlText.add(textoTweet.replaceAll("\\R+", " "));

                    MediaEntity[] media = s.getRetweetedStatus().getMediaEntities();
                    if (media.length == 0) {
                        urlImage.add(s.getUser().getId() + "+" + s.getId() + "-NO-IMAGE");
                    } else {
                        for (MediaEntity m : media) { //search trough your entities

                            if (m.getMediaURLHttps() != null) {
                                System.out.println(m.getMediaURLHttps());
                                urlImage.add(m.getMediaURLHttps()); //get your url!
                            }

                        }
                    }
                } else {

                    if (urls.length == 1) {
                        for (URLEntity LINK : urls) {

                            URLConnection conn;

                            URL inputURL;
                            try {
                                inputURL = new URL(LINK.getExpandedURL());
                            } catch (MalformedURLException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                conn = inputURL.openConnection();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            String location = conn.getHeaderField("location");

                            System.out.println("location " + location + newLine);
                            System.out.println("getHost().getHost() " + conn.getURL().getHost() + newLine);
                            System.out.println("conn.getURL() " + conn.getURL() + newLine);
                            System.out.println(s.getId() + " cantidad= " + urls.length + newLine);


                            if (location != null) {
                                int k = 0;
                                for (int i = 0; i < Constantes.direccion.length; i++) {
                                    if (location.contains(Constantes.direccion[i]) && !location.contains("https://www.twitter.com/")) {

                                        urlTweet.add(location);
                                        if (s.getUser().getId() == 14638581) { //biobio no null
                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            System.out.println("location != null biobio"+ imageUrlOpenGraph);
                                            if(imageUrlOpenGraph != null) {
                                                urlImage.add(imageUrlOpenGraph.replace("-1200x633.png", "-750x400.png"));
                                            }

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            Jaccard j2 = new Jaccard(2);

                                            System.out.println("location != null biobio"+ titleUrlOpenGraph);

                                            if(titleUrlOpenGraph != null){
                                                textoTweet = textoTweet.split("https")[0];
                                                System.out.println("similar biobio location != null " + j2.similarity(titleUrlOpenGraph, textoTweet));

                                                if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {

                                                    textoTweet = textoTweet.replaceAll("\\R+", " ");
                                                    urlText.add(titleUrlOpenGraph);
                                                } else {
                                                    textoTweet = textoTweet.split("https")[0];
                                                    System.out.println("textotweet1 " + textoTweet);
                                                    urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                }
                                            }
                                        }
                                        if (s.getUser().getId() == 201493641) { //adnradio no null

                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            // System.out.println("location != null adnradio"+ imageUrlOpenGraph);

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            if(imageUrlOpenGraph != null){
                                                urlImage.add(imageUrlOpenGraph);}

                                            Jaccard j2 = new Jaccard(2);
                                            System.out.println("location != null adnradio "+ titleUrlOpenGraph);



                                            textoTweet = textoTweet.split("https")[0];
                                            if(titleUrlOpenGraph != null) {
                                                System.out.println("similar adnradio 1" + j2.similarity(titleUrlOpenGraph, textoTweet));
                                                System.out.println(titleUrlOpenGraph);
                                                System.out.println(textoTweet);

                                                if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {
                                                    urlText.add(titleUrlOpenGraph);
                                                } else {
                                                    textoTweet = textoTweet.split("https")[0];
                                                    System.out.println("textotweet1 " + textoTweet);
                                                    urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                }
                                            }


                                        }
                                        if (s.getUser().getId() == 7668952) { //cooperativa no null
                                            if (!location.equals("https://www.cooperativa.cl/radioenvivo/")) {
                                                Document image = null;
                                                try {
                                                    Connection connect = Jsoup.connect(location);
                                                    connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                    connect.referrer("http://www.google.com");
                                                    connect.timeout(40000);
                                                    connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                    image = connect
                                                            .get();
                                                } catch (HttpStatusException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException | IOException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }

                                                String imageUrlOpenGraph = image.select("meta[rel=image_src]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("href").trim())
                                                        .orElse(null);

                                                System.out.println(imageUrlOpenGraph+"location cooperativa image");
                                                if (imageUrlOpenGraph == null) {
                                                    if (location.contains("alairelibre.cl")) {
                                                        imageUrlOpenGraph = image.select("meta[name=twitter:image:src]").stream()
                                                                .findFirst()
                                                                .map(doc -> doc.attr("content").trim())
                                                                .orElse(null);
                                                        urlImage.add(imageUrlOpenGraph);

                                                    } else {
                                                        imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                                .findFirst()
                                                                .map(doc -> doc.attr("content").trim())
                                                                .orElse(null);
                                                        urlImage.add(imageUrlOpenGraph);

                                                    }
                                                }else{
                                                    imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                            .findFirst()
                                                            .map(doc -> doc.attr("content").trim())
                                                            .orElse(null);
                                                    urlImage.add(imageUrlOpenGraph);

                                                }



                                                Document title = null;
                                                try {
                                                    Connection connect = Jsoup.connect(location);
                                                    connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                    connect.referrer("http://www.google.com");
                                                    connect.timeout(40000);
                                                    connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                    title = connect
                                                            .get();
                                                } catch (HttpStatusException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException | IOException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }

                                                String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("content").trim())
                                                        .orElse(null);

                                                if (titleUrlOpenGraph == null) {
                                                    if (location.contains("alairelibre.cl") && !location.contains("/stat/radioenvivo/")) {
                                                        titleUrlOpenGraph = title.select("meta[name=twitter:title]").stream()
                                                                .findFirst()
                                                                .map(doc -> doc.attr("content").trim())
                                                                .orElse(null);
                                                    } else {
                                                        titleUrlOpenGraph = title.select("meta[property=og:description]").stream()
                                                                .findFirst()
                                                                .map(doc -> doc.attr("content").trim())
                                                                .orElse(null);
                                                    }
                                                }
                                                Jaccard j2 = new Jaccard(2);


                                                textoTweet = textoTweet.split("https")[0];
                                                System.out.println("similar cooperativa " + j2.similarity(titleUrlOpenGraph, textoTweet));
                                                System.out.println(titleUrlOpenGraph);
                                                System.out.println(textoTweet);

                                                if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {
                                                    urlText.add(titleUrlOpenGraph);
                                                } else {
                                                    textoTweet = textoTweet.split("https")[0];
                                                    System.out.println("textotweet Cooperativa " + textoTweet);
                                                    urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                }






                                            }
                                        }


                                        if (s.getUser().getId() == 24952459) { //canal 13 no null

                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String imageUrlOpenGraph = image.select("meta[name=twitter:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            urlImage.add(imageUrlOpenGraph);

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);



                                            Jaccard j2 = new Jaccard(2);
                                            System.out.println("location != null t13 "+ titleUrlOpenGraph);


                                            textoTweet = textoTweet.split("https")[0];
                                            System.out.println("similar t13 " + j2.similarity(titleUrlOpenGraph, textoTweet));
                                            System.out.println(titleUrlOpenGraph);
                                            System.out.println(textoTweet);

                                            if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {
                                                urlText.add(titleUrlOpenGraph);
                                            } else {
                                                textoTweet = textoTweet.split("https")[0];
                                                System.out.println("textotweet1 " + textoTweet);
                                                urlText.add(textoTweet.replaceAll("\\R+", " "));
                                            }
                                        }
                                        if (s.getUser().getId() == 90227660) { //24horas

                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            //   System.out.println("location != null 24horas"+ imageUrlOpenGraph);
                                            urlImage.add(imageUrlOpenGraph);

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            textoTweet = textoTweet.split("https")[0];
                                            Jaccard j2 = new Jaccard(2);

                                            if(titleUrlOpenGraph!=null) {
                                                if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {
                                                    System.out.println(j2.similarity(titleUrlOpenGraph, textoTweet));
                                                    System.out.println("2 " + titleUrlOpenGraph);
                                                    textoTweet = textoTweet.replaceAll("\\R+", " ");
                                                    urlText.add(titleUrlOpenGraph);
                                                } else {
                                                    textoTweet = textoTweet.split("https")[0];
                                                    System.out.println("textotweetTVN " + textoTweet);
                                                    urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                }
                                            }

                                            //   System.out.println("location != null 24horas"+ titleUrlOpenGraph);

                                        }
                                        if (s.getUser().getId() == 51326671) { //chilevision


                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            // System.out.println("location != null chilevision"+ imageUrlOpenGraph);

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            //  System.out.println("location != null chilevision"+titleUrlOpenGraph);

                                            urlImage.add(imageUrlOpenGraph);
                                            urlText.add(titleUrlOpenGraph.replace(" - CHVNoticias.cl", ""));

                                        }
                                        if (s.getUser().getId() == 58048133) { //meganoticias



                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                connect.execute();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(location);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);
                                            urlImage.add(imageUrlOpenGraph);
                                            urlText.add(titleUrlOpenGraph);
                                            //  System.out.println(imageUrlOpenGraph);
                                            //  System.out.println(titleUrlOpenGraph + newLine);


                                        }
                                        if (s.getUser().getId() == 18248645) { //cnnchile

                                            if (!location.contains("/page/en-vivo/")) {
                                                Document image = null;
                                                try {
                                                    Connection connect = Jsoup.connect(location);
                                                    connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                    connect.referrer("http://www.google.com");
                                                    connect.timeout(40000);
                                                    connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                    image = connect
                                                            .get();
                                                } catch (HttpStatusException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException | IOException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }

                                                String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("content").trim())
                                                        .orElse(null);

                                                urlImage.add(imageUrlOpenGraph);

                                                Document title = null;
                                                try {
                                                    title = Jsoup.connect(location)
                                                            .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                                                            .referrer("http://www.google.com")
                                                            .timeout(40000) //it's in milliseconds, so this means 5 seconds.
                                                            .ignoreHttpErrors(true)
                                                            .get();
                                                } catch (HttpStatusException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException | IOException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }
                                                String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("content").trim())
                                                        .orElse(null);

                                                urlText.add(titleUrlOpenGraph);

                                            } else {
                                                textoTweet = textoTweet.split("https")[0];
                                                System.out.println("textotweetCNN" + textoTweet);
                                                urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                MediaEntity[] media = s.getMediaEntities();
                                                if (media.length == 0) {
                                                    urlImage.add(s.getUser().getId() + "+" + s.getId() + "-NO-IMAGE");
                                                } else {
                                                    for (MediaEntity m : media) { //search trough your entities

                                                        if (m.getMediaURLHttps() != null) {
                                                            System.out.println("CNN" + m.getMediaURLHttps());
                                                            urlImage.add(m.getMediaURLHttps()); //get your url!
                                                        }

                                                    }
                                                }
                                            }

                                        }
                                        k++;
                                    }


                                }
                                // System.out.println("k= "+ k);
                                if (k == 0) {
                                    urlTweet.add("https://twitter.com/" + s.getUser().getScreenName() + "/status/" + s.getId());
                                    urlText.add(("NO-TEXT"));
                                    urlImage.add("NO-IMAGE");


                                }


                            }


                            if (location == null) {
                                //  System.out.println("location es null");
                                int k = 0;
                                for (int i = 0; i < Constantes.direccion.length; i++) {
                                    if (conn.getURL().getHost().equals(Constantes.direccion[i]) && !conn.getURL().getHost().contains("https://twitter.com/")) {
                                        urlTweet.add(String.valueOf(conn.getURL()));
                                        if (s.getUser().getId() == 14638581) { //biobio null

                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);
                                            System.out.println("location == null biobio"+ imageUrlOpenGraph);

                                            if(imageUrlOpenGraph != null) {
                                                urlImage.add(imageUrlOpenGraph.replace("-1200x633.png", "-750x400.png"));
                                            }

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);



                                            Jaccard j2 = new Jaccard(2);
                                            System.out.println("location == null biobio"+ titleUrlOpenGraph);

                                            if(titleUrlOpenGraph != null) {
                                                textoTweet = textoTweet.split("https")[0];
                                                System.out.println("similar biobio  location = null" + j2.similarity(titleUrlOpenGraph, textoTweet));

                                                if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {

                                                    textoTweet = textoTweet.replaceAll("\\R+", " ");
                                                    urlText.add(titleUrlOpenGraph);
                                                } else {
                                                    textoTweet = textoTweet.split("https")[0];
                                                    System.out.println("textotweet1 " + textoTweet);
                                                    urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                }
                                            }

                                        }
                                        if (s.getUser().getId() == 201493641) { //adnradio null

                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);


                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            if(imageUrlOpenGraph != null){
                                                urlImage.add(imageUrlOpenGraph);}

                                            Jaccard j2 = new Jaccard(2);
                                            System.out.println("location = null adnradio "+ titleUrlOpenGraph);


                                            textoTweet = textoTweet.split("https")[0];
                                            if(titleUrlOpenGraph != null) {
                                                System.out.println("similar adnradio 2 " + j2.similarity(titleUrlOpenGraph, textoTweet));
                                                System.out.println(titleUrlOpenGraph);
                                                System.out.println(textoTweet);

                                                if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {
                                                    urlText.add(titleUrlOpenGraph);
                                                } else {
                                                    textoTweet = textoTweet.split("https")[0];
                                                    System.out.println("textotweet2 " + textoTweet);
                                                    urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                }
                                            }

                                        }
                                        if (s.getUser().getId() == 7668952) { //cooperativa null
                                            if (!conn.getURL().equals("https://www.cooperativa.cl/radioenvivo/")) {
                                                Document image = null;
                                                try {
                                                    Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                    connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                    connect.referrer("http://www.google.com");
                                                    connect.timeout(40000);
                                                    connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                    image = connect
                                                            .get();
                                                } catch (HttpStatusException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException | IOException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }

                                                String imageUrlOpenGraph = image.select("meta[rel=image_src]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("href").trim())
                                                        .orElse(null);

                                                if (imageUrlOpenGraph == null) {
                                                    if (conn.getURL().getHost().contains("alairelibre.cl")) {
                                                        imageUrlOpenGraph = image.select("meta[name=twitter:image:src]").stream()
                                                                .findFirst()
                                                                .map(doc -> doc.attr("content").trim())
                                                                .orElse(null);
                                                        urlImage.add(imageUrlOpenGraph);
                                                    } else {
                                                        imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                                .findFirst()
                                                                .map(doc -> doc.attr("content").trim())
                                                                .orElse(null);
                                                        urlImage.add(imageUrlOpenGraph);
                                                    }
                                                } else {
                                                    imageUrlOpenGraph = image.select("meta[name=twitter:image:src]").stream()
                                                            .findFirst()
                                                            .map(doc -> doc.attr("content").trim())
                                                            .orElse(null);
                                                    urlImage.add(imageUrlOpenGraph);
                                                }


                                                Document title = null;
                                                try {
                                                    Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                    connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                    connect.referrer("http://www.google.com");
                                                    connect.timeout(40000);
                                                    connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                    title = connect
                                                            .get();
                                                } catch (HttpStatusException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException | IOException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }
                                                String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("content").trim())
                                                        .orElse(null);

                                                if (titleUrlOpenGraph == null) {
                                                    if (conn.getURL().getHost().contains("alairelibre.cl") && !conn.getURL().getHost().contains("/stat/radioenvivo/")) {
                                                        titleUrlOpenGraph = title.select("meta[name=twitter:title]").stream()
                                                                .findFirst()
                                                                .map(doc -> doc.attr("content").trim())
                                                                .orElse(null);
                                                    } else {
                                                        titleUrlOpenGraph = title.select("meta[property=og:description]").stream()
                                                                .findFirst()
                                                                .map(doc -> doc.attr("content").trim())
                                                                .orElse(null);
                                                    }
                                                }
                                                Jaccard j2 = new Jaccard(2);

                                                textoTweet = textoTweet.split("https")[0];
                                                System.out.println("similar cooperativa2 " + j2.similarity(titleUrlOpenGraph, textoTweet));
                                                System.out.println(titleUrlOpenGraph);
                                                System.out.println(textoTweet);

                                                if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {
                                                    urlText.add(titleUrlOpenGraph);
                                                } else {
                                                    textoTweet = textoTweet.split("https")[0];
                                                    System.out.println("textotweet Cooperativa2 " + textoTweet);
                                                    urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                }


                                            }
                                        }

                                        if (s.getUser().getId() == 24952459) { //canal 13 null

                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String imageUrlOpenGraph = image.select("meta[name=twitter:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);
                                            urlImage.add(imageUrlOpenGraph);

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            Jaccard j2 = new Jaccard(2);
                                            System.out.println("location = null t13 "+ titleUrlOpenGraph);


                                            textoTweet = textoTweet.split("https")[0];
                                            System.out.println("similar t13 null " + j2.similarity(titleUrlOpenGraph, textoTweet));
                                            System.out.println(titleUrlOpenGraph);
                                            System.out.println(textoTweet);

                                            if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {
                                                urlText.add(titleUrlOpenGraph);
                                            } else {
                                                textoTweet = textoTweet.split("https")[0];
                                                System.out.println("textotweet1 " + textoTweet);
                                                urlText.add(textoTweet.replaceAll("\\R+", " "));
                                            }
                                        }


                                        if (s.getUser().getId() == 90227660) { //24horas null

                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                image = connect.get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            urlImage.add(imageUrlOpenGraph);

                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);


                                            textoTweet = textoTweet.split("https")[0];
                                            Jaccard j2 = new Jaccard(2);

                                            if(titleUrlOpenGraph!=null) {
                                                if (j2.similarity(titleUrlOpenGraph, textoTweet) > 0.7) {
                                                    System.out.println(j2.similarity(titleUrlOpenGraph, textoTweet));
                                                    System.out.println("2 " + titleUrlOpenGraph);
                                                    textoTweet = textoTweet.replaceAll("\\R+", " ");
                                                    urlText.add(titleUrlOpenGraph);
                                                } else {
                                                    textoTweet = textoTweet.split("https")[0];
                                                    System.out.println("textotweetTVN " + textoTweet);
                                                    urlText.add(textoTweet.replaceAll("\\R+", " "));
                                                }
                                            }




                                        }
                                        if (s.getUser().getId() == 51326671) { //chilevision

                                            if (!conn.getURL().getHost().contains("/page/en-vivo/")) {
                                                Document image = null;
                                                try {
                                                    image = Jsoup.connect(String.valueOf(conn.getURL()))
                                                            .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                                                            .referrer("http://www.google.com")
                                                            .timeout(40000) //it's in milliseconds, so this means 5 seconds.
                                                            .ignoreHttpErrors(true)
                                                            .get();
                                                } catch (HttpStatusException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException | IOException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }
                                                String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("content").trim())
                                                        .orElse(null);


                                                Document title = null;
                                                try {
                                                    Connection connect = Jsoup.connect(String.valueOf(conn.getURL()));
                                                    connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                    connect.referrer("http://www.google.com");
                                                    connect.timeout(40000);
                                                    connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                    title = connect
                                                            .get();
                                                } catch (HttpStatusException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException | IOException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                                }
                                                String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("content").trim())
                                                        .orElse(null);

                                                //  System.out.println(imageUrlOpenGraph);
                                                //  System.out.println(titleUrlOpenGraph + newLine);
                                                urlImage.add(imageUrlOpenGraph);
                                                urlText.add(titleUrlOpenGraph.replace(" - CHVNoticias.cl", ""));

                                            }
                                        }
                                        if (s.getUser().getId() == 58048133) { //meganoticias
/*
                                            System.out.println(String.valueOf(conn.getURL())+"site mage");
                                            urlImage.add("test mega");
                                            urlText.add("rffdf");
                                            */
                                            String url1 = new Scanner(conn.getURL().toString()).useDelimiter("\\?").next();
                                            System.out.println(url1);

                                            Document image = null;
                                            try {
                                                Connection connect = Jsoup.connect(url1);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 10 seconds.
                                                image = connect.execute().parse();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);


                                            Document title = null;
                                            try {
                                                Connection connect = Jsoup.connect(url1);
                                                connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
                                                connect.referrer("http://www.google.com");
                                                connect.timeout(40000);
                                                connect.ignoreHttpErrors(true);//it's in milliseconds, so this means 5 seconds.
                                                title = connect
                                                        .get();

                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);


                                            if (imageUrlOpenGraph == null) {
                                                String imageUrlOpenGraph2 = image.select("meta[property=og:image]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("content").trim())
                                                        .orElse(null);

                                                //   System.out.println("imageURL999" + imageUrlOpenGraph2);
                                                if (imageUrlOpenGraph2 != null) {
                                                    urlImage.add(imageUrlOpenGraph2);
                                                }
                                            }
                                            if (titleUrlOpenGraph == null) {
                                                String titleUrlOpenGraph2 = title.select("meta[property=og:description]").stream()
                                                        .findFirst()
                                                        .map(doc -> doc.attr("content").trim())
                                                        .orElse(null);
                                                //  System.out.println("imageURL999" + imageUrlOpenGraph2);

                                                urlText.add(titleUrlOpenGraph2);
                                            }
                                            if (imageUrlOpenGraph != null) {
                                                urlImage.add(imageUrlOpenGraph);
                                            }
                                            if (titleUrlOpenGraph != null) {
                                                urlText.add(titleUrlOpenGraph);
                                            }


                                        }

                                        if (s.getUser().getId() == 18248645) { //cnnchile

                                            Document image = null;
                                            try {
                                                image = Jsoup.connect(String.valueOf(conn.getURL()))
                                                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                                                        .referrer("http://www.google.com")
                                                        .timeout(40000) //it's in milliseconds, so this means 5 seconds.
                                                        .ignoreHttpErrors(true)
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }

                                            String imageUrlOpenGraph = image.select("meta[property=og:image]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            urlImage.add(imageUrlOpenGraph);

                                            Document title = null;
                                            try {
                                                title = Jsoup.connect(String.valueOf(conn.getURL()))
                                                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                                                        .referrer("http://www.google.com")
                                                        .timeout(40000) //it's in milliseconds, so this means 5 seconds.
                                                        .ignoreHttpErrors(true)
                                                        .get();
                                            } catch (HttpStatusException e) {
                                                e.printStackTrace();
                                            } catch (NullPointerException | IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            String titleUrlOpenGraph = title.select("meta[property=og:title]").stream()
                                                    .findFirst()
                                                    .map(doc -> doc.attr("content").trim())
                                                    .orElse(null);

                                            urlText.add(titleUrlOpenGraph);
                                            //   System.out.println(imageUrlOpenGraph);
                                            //  System.out.println(titleUrlOpenGraph + newLine);
                                        }
                                        k++;
                                    }

                                }
                                //   System.out.println("k= "+ k);
                                if (k == 0) {
                                    urlTweet.add("https://twitter.com/" + s.getUser().getScreenName() + "/status/" + s.getId());
                                    urlImage.add("NO-IMAGE");
                                    urlText.add(("NO-TEXT"));

                                }
                            }


                        }

                    }

                    if (urls.length == 0) {
                        urlTweet.add("https://twitter.com/" + s.getUser().getScreenName() + "/status/" + s.getId());
                        if (isRetweet) {
                            textoTweet = s.getRetweetedStatus().getText().trim();
                            System.out.println("textotweet" + textoTweet);
                            textoTweet = textoTweet.split("https")[0];
                            System.out.println("textotweet" + textoTweet);
                            urlText.add(textoTweet.replaceAll("\\R+", " "));
                            MediaEntity[] media = s.getRetweetedStatus().getMediaEntities();
                            if (media.length == 0) {
                                urlImage.add(s.getUser().getId() + "+" + s.getId() + "-NO-IMAGE");
                            } else {
                                for (MediaEntity m : media) { //search trough your entities

                                    if (m.getMediaURLHttps() != null) {
                                        for(int i=0; i<2;i++) {
                                            System.out.println(m.getMediaURLHttps());
                                            urlImage.add(m.getMediaURLHttps()); //get your url!}
                                        }
                                    }

                                }
                            }
                            //urlImage.add("NO-IMAGE");
                        } else {
                            textoTweet = textoTweet.split("https")[0];
                            System.out.println("textotweet" + textoTweet);
                            urlText.add(textoTweet.replaceAll("\\R+", " "));
                            MediaEntity[] media = s.getMediaEntities();
                            if (media.length == 0) {
                                urlImage.add(s.getUser().getId() + "+" + s.getId() + "-NO-IMAGE");
                            } else {
                                for (MediaEntity m : media) { //search trough your entities

                                    if (m.getMediaURLHttps() != null) {
                                        System.out.println(m.getMediaURLHttps());
                                        urlImage.add(m.getMediaURLHttps()); //get your url!
                                    }

                                }
                            }
                            // urlImage.add("NO-IMAGE");
                        }

                    }
                    if (urls.length > 1) {

                        urlTweet.add("https://twitter.com/" + s.getUser().getScreenName() + "/status/" + s.getId());
                        MediaEntity[] media = s.getMediaEntities();
                        if (media.length == 0) {
                            urlImage.add(s.getUser().getId() + "+" + s.getId() + "-NO-IMAGE");
                        } else {
                            for (MediaEntity m : media) { //search trough your entities

                                if (m.getMediaURLHttps() != null) {
                                    System.out.println(m.getMediaURLHttps());
                                    urlImage.add(m.getMediaURLHttps()); //get your url!
                                }

                            }
                        }


                        if (s.getUser().getId() == 58048133) {
                            textoTweet=textoTweet.replaceAll("\\R+", " ");
                            System.out.println(textoTweet+"qqq");
                            String textoTweet2= StringUtils.substringBetween(textoTweet,"\uD83D\uDCFA #MeganoticiasPrime | "," \uD83D\uDCE1 Señal en vivo ");

                            System.out.println("textotweet mega +2 links " + textoTweet2);
                            urlText.add(textoTweet2);

                        }else {
                            urlText.add(textoTweet.replaceAll("\\R+", " "));
                        }
                    }
                }
            }


        }
        // if(statusTweet.size() != urlTweet.size() && urlText.size() != urlImage.size()) {
        System.out.println("status " + statusTweet.size() + statusTweet);
        System.out.println("urltweet " + urlTweet.size() + urlTweet);
        System.out.println("urlText " + urlText.size() + urlText);
        System.out.println("urlImage " + urlImage.size() + urlImage);
        //  }


        return new ListasFin(modTexto, fechaTweet, idCuenta, statusTweet, urlTweet, urlText, urlImage);
    }

    public static Maps lectura() {

        File fileUnicos = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "unicos " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
        FileReader fileUnicos2;

        try {

            // fileReader = new FileReader("unicos 2022-10-02.txt");

            fileUnicos2 = new FileReader(fileUnicos);

            // fileReader = new FileReader("unicos 2022-10-05.txt");

            //fileReader = new FileReader("unicos 2022 09 13.txt");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String lineFile;
        BufferedReader bufferedReader1;


        LinkedHashMultimap<String, String> jaccardResultsUnicos = LinkedHashMultimap.create();
        LinkedHashMultimap<String, String> jaccardResultsCopia = LinkedHashMultimap.create();
        List<String> lineas = new ArrayList<>();

        ImmutableMultimap.Builder<String, String> builderFinal = new ImmutableMultimap.Builder<>();

        try {

            bufferedReader1 = new BufferedReader(fileUnicos2);


            while ((lineFile = bufferedReader1.readLine()) != null) {
                lineas.add(lineFile);
                String[] splited = lineFile.split("-");
                String primero = splited[0];
                String segundo = splited[1];

                jaccardResultsUnicos.put(primero, segundo);
                jaccardResultsCopia.put(segundo, primero);


            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        LinkedHashMultimap<String, String> jaccardResultsFinal = LinkedHashMultimap.create();

        Set<String> llavesUnicos = jaccardResultsUnicos.keys().elementSet();

        LinkedHashMultimap<String, String> jaccardResultsFinalCopia = LinkedHashMultimap.create();

        for (String llave : llavesUnicos) {
            for (String valor : jaccardResultsUnicos.get(llave)) {
                if (!jaccardResultsFinal.containsKey(llave) && !jaccardResultsFinal.containsValue(llave)) {
                    if (!jaccardResultsFinal.containsKey(valor) && !jaccardResultsFinal.containsValue(valor)) {
                        jaccardResultsFinal.put(llave, valor);
                        jaccardResultsFinalCopia.put(valor, llave);
                    }
                } //VALORES UNICOS

                if (jaccardResultsFinal.containsKey(llave) && !jaccardResultsFinal.containsValue(llave)) { //SI LLAVE EXISTE COMO KEY PERO NO EXISTE COMO VALOR
                    // System.out.println("2 jRF"+jaccardResultsFinal);
                    // System.out.println("2.1 jRF"+jaccardResultsFinalCopia);
                    // System.out.println(llave+" "+valor);

                    if (!jaccardResultsFinal.containsKey(valor) && !jaccardResultsFinal.containsValue(valor)) { //SI NO EXISTE VALOR COMO KEY Y VALUE
                        jaccardResultsFinal.put(llave, valor);
                        jaccardResultsFinalCopia.put(valor, llave);
                        //  System.out.println("2"+llave+" "+valor);
                    }
                }

                if (jaccardResultsFinal.containsValue(llave) && !jaccardResultsFinal.containsValue(valor) && !jaccardResultsFinal.containsKey(valor)) {
                    //  System.out.println(llave+" zxc "+valor);
                    // System.out.println("3 jRF"+jaccardResultsFinal);
                    // System.out.println("3.1 jRF"+jaccardResultsFinalCopia);
                    for (String s : jaccardResultsCopia.get(llave)) {
                        //  System.out.println(s+" "+valor);
                        if (jaccardResultsFinal.containsKey(s)) {
                            jaccardResultsFinal.put(s, valor);
                            jaccardResultsFinalCopia.put(valor, s);
                        }
                        if (jaccardResultsFinalCopia.containsKey(s)) {
                            //   System.out.println(s+"es llave en copia");
                            for (String z : jaccardResultsFinalCopia.get(s)) {

                                if (jaccardResultsFinal.containsKey(z)) {
                                    jaccardResultsFinal.put(z, valor);
                                    jaccardResultsFinalCopia.put(valor, z);
                                }
                            }
                        }
                        //  System.out.println("else "+jaccardResultsFinalCopia);


                    }
                }

                if (jaccardResultsFinal.containsValue(valor) && !jaccardResultsFinal.containsValue(llave) && !jaccardResultsFinal.containsKey(llave)) {

                    // System.out.println(llave+" rty "+valor);
                    for (String s : jaccardResultsCopia.get(valor)) {
                        // System.out.println(s+" "+llave);
                        if (jaccardResultsFinal.containsKey(s)) {
                            // System.out.println(s+" put "+llave);
                            jaccardResultsFinal.put(s, llave);
                            jaccardResultsFinalCopia.put(llave, s);
                        }

                        if (jaccardResultsFinalCopia.containsKey(valor)) {
                            for (String z : jaccardResultsFinalCopia.get(valor)) {
                                if (jaccardResultsFinal.containsKey(z)) {
                                    jaccardResultsFinal.put(z, llave);
                                    jaccardResultsFinalCopia.put(llave, z);
                                }
                            }
                        }
                        // System.out.println("else "+jaccardResultsFinalCopia);
                    }
                }

                if (jaccardResultsFinal.containsKey(valor) && !jaccardResultsFinal.containsValue(llave) && !jaccardResultsFinal.containsKey(llave)) {

                    //System.out.println(llave+" VBN "+valor);
                    // System.out.println("5 jRF"+jaccardResultsFinal);
                    //System.out.println("5.1 jRF"+jaccardResultsFinalCopia);
                    jaccardResultsFinal.put(valor, llave);
                    jaccardResultsFinalCopia.put(llave, valor);
                }

            }
        }

        System.out.println("PRUEBA JACCARD FINAL" + jaccardResultsFinal);

        return new Maps(jaccardResultsFinal);

    }
        @Override
    public void run(){

            System.out.println("##########  " + "Contador: " + counter + "  " + INSTANT_FORMATTER3.format(Instant.now()) + "  ##########");
            File directory = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()));

            if (!directory.exists()) {
                directory.mkdirs();
            }

            List<String> mod2Texto = new ArrayList<>();
            LinkedHashSet<String> mod3Texto = new LinkedHashSet<>();
            List<String> mod4Texto = new ArrayList<>();
            LinkedHashSet<String> mod5Texto = new LinkedHashSet<>();
            LinkedHashSet<String> borrador = new LinkedHashSet<>();
            LinkedHashSet<String> borrador2 = new LinkedHashSet<>();


            Twitter twitter = new TwitterFactory(setupConfigurationBuilder()).getInstance();
            String newLine = System.getProperty("line.separator");


//EScribir en un archivo txt los tweets que tienen semejanzas-

            FileWriter fileWriter = null;
            FileWriter fileUrl = null;
            FileWriter fileDate = null;
            FileWriter fileUrlImage = null;
            FileWriter fileUrlText = null;

            File fileData = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "data " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
            File fileUrlsD = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "urls " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
            File fileDateD = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "date " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
            File fileImageD = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "urlsImage " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");
            File fileTextD = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "urlsText " + INSTANT_FORMATTER2.format(Instant.now()) + ".txt");


            try {
                fileWriter = new FileWriter(fileData.getAbsoluteFile(), true);
                fileUrl = new FileWriter(fileUrlsD.getAbsoluteFile(), true);
                fileDate = new FileWriter(fileDateD.getAbsoluteFile(), true);
                fileUrlImage = new FileWriter(fileImageD.getAbsoluteFile(), true);
                fileUrlText = new FileWriter(fileTextD.getAbsoluteFile(), true);

            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileWriter != null;
            assert fileUrl != null;
            assert fileDate != null;
            assert fileUrlImage != null;
            assert fileUrlText != null;

            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bw);
            BufferedWriter bwUrl = new BufferedWriter(fileUrl);
            PrintWriter printUrl = new PrintWriter(bwUrl);
            BufferedWriter dt = new BufferedWriter(fileDate);
            PrintWriter printDate = new PrintWriter(dt);
            BufferedWriter bwUrlImage = new BufferedWriter(fileUrlImage);
            PrintWriter printUrlImage = new PrintWriter(bwUrlImage);
            BufferedWriter bwUrlText = new BufferedWriter(fileUrlText);
            PrintWriter printUrlText = new PrintWriter(bwUrlText);


            ListasFin valor = resultados();
            for (int l = 0; l < Constantes.usuario.length * 3; l++) {
                mod2Texto.add(valor.getModTexto().get(l).replace("ñ", "\001").replace("http", " "));// Reemplaza las ñ con el caracter ^A para despues rescatarlas y reemplaza http con espacio en blanco para deshacer de urls.
                mod3Texto.addAll(mod2Texto); // La implementación mod2Texto2, del tipo linkedhashset, es para la eliminación de elementos duplicados
                mod2Texto.clear(); // Se borran todos los elementos de mod2Texto
                mod2Texto.addAll(mod3Texto); // Se rellena mod2Texto con los datos almacenados en mod2Texto2
                try {
                    mod4Texto.add(StringUtils.stripAccents(String.valueOf(mod2Texto.get(l)))); // Elimina las acentos que se encuentren en los strings almacenados en mod2Texto
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                mod5Texto.addAll(mod4Texto); //La misma función que desempeña mod2Texto2
                mod4Texto.clear();
                mod4Texto.addAll(mod5Texto);// Se rellena mod4Texto con los datos almacenados en mod4Texto2
                borrador.add((mod4Texto.get(l)).replace("\001", "ñ")); //se recuperan las ñ que fueron reemplazadas por el caracter A^ anteriormente
                borrador2.addAll(borrador);
                borrador.clear();
                borrador.addAll(borrador2);
            }


            Jaccard j2 = new Jaccard(3);
            Jaccard j3 = new Jaccard(4);


            String[] textoPrueba = borrador.toArray(new String[0]); // Pasa de lista a un arreglo vacio del tipo string para poder llamarlos de a uno.

            for (int u = 0; u < Constantes.usuario.length * 3; u++) {
                for (String m : Constantes.merma) {
                    textoPrueba[u] = textoPrueba[u].replaceAll("\\b" + m + "\\b", ""); // quita elementos no esenciales para la comparación desde un arreglo del tipo string
                }
            }


            //El método toString() devuelve una cadena de caracteres representando el array especificado y sus elementos
            for (int x = 0; x < textoPrueba.length; x++) {

                for (int y = 0; y < textoPrueba.length; y++) {
                    if (!(valor.getIdCuenta().get(x)).equals(valor.getIdCuenta().get(y))) {
                        if (j2.similarity(textoPrueba[x], textoPrueba[y]) > 0.225 && j3.similarity(textoPrueba[x], textoPrueba[y]) > 0.155) {
                            if (valor.getFechaTweet().get(x).before(valor.getFechaTweet().get(y))) {
                                //DecimalFormat df = new DecimalFormat("#.0000");
                                System.out.println(valor.getFechaTweet().get(x) + " es antes de " + valor.getFechaTweet().get(y));
                                System.out.println(valor.getStatusTweet().get(x) + "    " + valor.getStatusTweet().get(y));


                                System.out.println(x + "  " + valor.getUrlTweet().get(x) + newLine + y + " " + valor.getUrlTweet().get(y) + newLine + "-------------------");
                                //   int similaridad = (int) (1000000 * Double.parseDouble(df.format(j2.similarity(textoPrueba[x], textoPrueba[y]))));
                                System.out.println("Similaridad" + "  " + j2.similarity(textoPrueba[x], textoPrueba[y]));
                                System.out.println("Similaridad 2" + "  " + j3.similarity(textoPrueba[x], textoPrueba[y]));
                                System.out.println(valor.getIdCuenta().get(x) + "-" + valor.getStatusTweet().get(x) + "-" + valor.getIdCuenta().get(y) + "-" + valor.getStatusTweet().get(y));


                                printWriter.print(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "-" + valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + newLine);
                                printUrl.print(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "#" + valor.getUrlTweet().get(x) + newLine);
                                printUrl.print(valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + "#" + valor.getUrlTweet().get(y) + newLine);
                                printDate.print(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "-" + valor.getFechaTweet().get(x).getTime() + newLine);
                                printDate.print(valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + "-" + valor.getFechaTweet().get(y).getTime() + newLine);

                                System.out.println(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "#" + valor.getUrlTweet().get(x) + newLine);
                                System.out.println(valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + "#" + valor.getUrlTweet().get(y) + newLine);
                                System.out.println(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "-" + valor.getFechaTweet().get(x).getTime() + newLine);
                                System.out.println(valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + "-" + valor.getFechaTweet().get(y).getTime() + newLine);

                                System.out.println(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "#" + valor.getUrlImage().get(x) + newLine);
                                printUrlImage.print(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "#" + valor.getUrlImage().get(x) + newLine);
                                printUrlImage.print(valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + "#" + valor.getUrlImage().get(y) + newLine);
                                printUrlText.print(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "Æ" + valor.getUrlText().get(x) + newLine);
                                printUrlText.print(valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + "Æ" + valor.getUrlText().get(y) + newLine);

                                System.out.println(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "#" + valor.getUrlImage().get(x) + newLine);
                                System.out.println(valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + "#" + valor.getUrlImage().get(y) + newLine);
                                System.out.println(valor.getIdCuenta().get(x) + "+" + valor.getStatusTweet().get(x) + "Æ" + valor.getUrlText().get(x) + newLine);
                                System.out.println(valor.getIdCuenta().get(y) + "+" + valor.getStatusTweet().get(y) + "Æ" + valor.getUrlText().get(y) + newLine);


                            }

                        }
                    }
                    y++;
                }


                //tw_writer.close();
            }

            Archivo();


            printUrl.close();
            printDate.close();
            printUrlImage.close();
            printUrlText.close();
            printWriter.close();


            counter++;
            // LinkedListMultimap<String, Serie> newsJson = LinkedListMultimap.create();

            Maps valFin = lectura();
            Unicos unicos = reading();
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .setPrettyPrinting()
                    .enableComplexMapKeySerialization()
                    .create();


            FileWriter fileWriter2;
            File jsonFile = new File(directorio + "Noticias.json");
            //File jsonFile = new File(directorio + INSTANT_FORMATTER2.format(Instant.now()) + "/" + "Noticias " + INSTANT_FORMATTER.format(Instant.now()) + ".json");

            try {
                // fileWriter2 = new FileWriter("Student 2022-10-05.json");
                fileWriter2 = new FileWriter(jsonFile.getAbsoluteFile(), false);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            ArrayList<String> listJaccard = new ArrayList<>(valFin.getJaccardResultsFinal().keySet());
            Collections.reverse(listJaccard); //oder de llaves inverso
            Iterator keyIterator = listJaccard.iterator();

            while (keyIterator.hasNext()) {
                List<Info> jsonFiles = new ArrayList<>();
                String key = (String) keyIterator.next();
                Set<String> values = valFin.getJaccardResultsFinal().get(key);

                // System.out.println("key= "+key+" values= "+values);
                if (unicos.getFileDateD().containsKey(key) && unicos.getFileUrlD().containsKey(key) && unicos.getFileImageD().containsKey(key) && unicos.getFileTextD().containsKey(key)) {

                    String[] idStatus = key.split("\\+");
                    Info archivo = new Info(idStatus[0], idStatus[1], unicos.getFileDateD().get(key).toString().replace("[", "").replace("]", ""), unicos.getFileUrlD().get(key).toString().replace("[", "").replace("]", ""), unicos.getFileImageD().get(key).toString().replace("[", "").replace("]", ""), unicos.getFileTextD().get(key).toString().replace("[", "").replace("]", ""));
                    jsonFiles.add(archivo);

                }
                for (String value : values) {
                    if (unicos.getFileDateD().containsKey(value) && unicos.getFileUrlD().containsKey(value) && unicos.getFileImageD().containsKey(value) && unicos.getFileTextD().containsKey(value)) {

                        String[] idStatus2 = value.split("\\+");
                        Info archivo2 = new Info(idStatus2[0], idStatus2[1], unicos.getFileDateD().get(value).toString().replace("[", "").replace("]", ""), unicos.getFileUrlD().get(value).toString().replace("[", "").replace("]", ""), unicos.getFileImageD().get(value).toString().replace("[", "").replace("]", ""), unicos.getFileTextD().get(value).toString().replace("[", "").replace("]", ""));

                        jsonFiles.add(archivo2);


                    }
                }
                Serie prueba = new Serie(key, jsonFiles);
                newsJson.add(prueba);
                keyIterator.remove();
            }




            // ArrayList<LinkedHashSet<Serie>> list = new ArrayList<>();
            //  list.add(newsJson);


            gson.toJson(newsJson, fileWriter2);
            newsJson.clear();

            try {
                fileWriter2.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("JSON string write to a file successfully");
            System.out.println("time= " + Calendar.getInstance().getTime());
            //SaveData();
        }



}
