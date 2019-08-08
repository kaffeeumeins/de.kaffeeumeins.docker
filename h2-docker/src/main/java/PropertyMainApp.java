import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.PlatformManagedObject;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

public class PropertyMainApp {

    public static void main(String... args) throws Exception {
        System.out.println("Start Java");

        System.out.println("\nCommand line args =====================================================================");
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("\t" + i + " = \"" + args[i] + "\"");
            }
        }

        System.out.println("\nSystem.getenv() =========================================================================");
        printMap("\t", System.getenv());

        System.out.println("\nSystem.getProperties() ==================================================================");
        printMap("\t", System.getProperties());

        System.out.println("\nRuntime.getRuntime() ====================================================================");
        printMap("\t", runtime());

        System.out.println("\nRuntimeMXBean ====================================================================");
        printMap("\t", mxBean(RuntimeMXBean.class));

        System.out.println("\nMemoryMXBean ====================================================================");
        printMap("\t", mxBean(MemoryMXBean.class));

        String url = get("url", "jdbc:h2:tcp://localhost:8002/test", args);

        try (Connection con = DriverManager.
                getConnection(url, "sa", "")) {
            // add application code here
            try (ResultSet rs = con.createStatement().executeQuery("select * from INFORMATION_SCHEMA.CATALOGS")) {

            }
        }
    }


    public static String get(String key, String defValue, String[] args) {


        String argsValue = null;
        String propValue = System.getProperty(key);
        String envValue = System.getenv(key);
        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (arg.startsWith("-url=")) {
                    argsValue = arg.substring(5);
                }
            }
        }

        System.out.println("Program.argument(" + key + ").. = \"" + argsValue + "\"");
        System.out.println("System.getProperty(" + key + ") = \"" + propValue + "\"");
        System.out.println("System.getenv(" + key + ")..... = \"" + envValue + "\"");
        System.out.println("Default.value.................. = \"" + defValue + "\"");

        if (argsValue != null) {
            argsValue = argsValue.trim();
            if (argsValue.isEmpty() == false) {
                System.out.println("use Program.argument(" + argsValue + ")");
                return argsValue;
            }
        }
        System.out.println("Program.argument: value is null or empty");

        if (propValue != null) {
            propValue = propValue.trim();
            if (propValue.isEmpty() == false) {
                System.out.println("use System.getProperty(" + propValue + ")");
                return propValue;
            }
        }
        System.out.println("System.getProperty: value is null or empty");

        if (envValue != null) {
            envValue = envValue.trim();
            if (envValue.isEmpty() == false) {
                System.out.println("use System.getenv(" + envValue + ")");
                return envValue;
            }
        }
        System.out.println("System.getenv: value is null or empty");

        System.out.println("use Default.value(" + defValue + ")");
        return defValue;
    }

    private static String checkEmpty(String value) {
        if (value == null) {
            return null;
        }
        value = value.trim();
        return value.isEmpty() ? null : value;
    }

    public static Map<String, Object> runtime() {
        Map<String, Object> map = new TreeMap<>();

        long MB = 1024 * 1024;
        map.put("availableProcessors", (long) Runtime.getRuntime().availableProcessors());
        map.put("maxMemory...in.MB", Runtime.getRuntime().maxMemory() / MB);
        map.put("totalMemory.in.MB", Runtime.getRuntime().totalMemory() / MB);
        map.put("freeMemory..in.MB", Runtime.getRuntime().freeMemory() / MB);
        map.put("version", Runtime.version());
        return map;
    }

/*
    public static Map<String, Object> mxRuntime() {
        Map<String, Object> map = new TreeMap<>();

        RuntimeMXBean runtime = ManagementFactory.getPlatformMXBean(RuntimeMXBean.class);


        runtime.getBootClassPath();
        runtime.getBootClassPath();
        runtime.getClassPath();
        runtime.getInputArguments();
        runtime.getLibraryPath();
        runtime.getManagementSpecVersion();
        runtime.getName();
        runtime.getPid();
        runtime.getSpecName();
        runtime.getSpecVendor();
        runtime.getSpecVersion();
        runtime.getStartTime();
        runtime.getSystemProperties();
        runtime.getUptime();
        runtime.getVmName();
        runtime.getVmVendor();
        runtime.getVmVersion();

        return map;
    } */


    private static void put(Map<String, Object> m, String key, Object value) {
        if (value instanceof Collection) {
            int i = 0;
            for (Object o : (Collection) value) {
                m.put(key + "." + i, o);
                i++;
            }
        } else if (value instanceof Map) {
            ((Map) value).forEach((k, v) -> m.put(key + "." + k, v));
        } else {
            m.put(key, value);
        }

    }

    private static <T extends PlatformManagedObject> Map<String, Object> mxBean(Class<T> cls) {
        Map<String, Object> map = new TreeMap<>();
        try {
            T obj = ManagementFactory.getPlatformMXBean(cls);
            map = bean(obj);
        } catch (Exception e) {
            map.put("exception", e.getMessage());
        }
        return map;
    }

    private static Map<String, Object> bean(Object obj) {
        Map<String, Object> map = new TreeMap<>();
        if (obj != null && obj.getClass() != null) {
            Method[] methods = obj.getClass().getMethods();
            if (methods != null && methods.length > 0) {
                for (Method m : obj.getClass().getMethods()) {
                    if (m.getName().startsWith("is")) {
                        put(map, m.getName().substring(2), invoke(m, obj));
                    } else if (m.getName().startsWith("get")) {
                        put(map, m.getName().substring(3), invoke(m, obj));
                    }
                }
            }
        }
        return map;
    }

    private static Object invoke(Method m, Object obj) {
        m.setAccessible(true);
        try {
            Object o = m.invoke(obj);
            if (o.getClass().isArray()) {
                o = Arrays.toString((Object[]) o);
            }
            return o;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static <K, V> void printMap(String prefix, Map<K, V> source) {
        // sort source
        Map<String, V> map = new TreeMap<>();
        source.forEach((k, v) -> map.put(Objects.toString(k), v));

        // print sorted source
        final int maxLength = maxLength(map.keySet());
        map.forEach((k, v) -> {
            System.out.println(prefix + keyWithDots(k, maxLength) + " = " + Objects.toString(v));
        });
    }

    private static <T> int maxLength(Collection<String> c) {
        int maxLength = 0;
        for (String k : c) {
            int length = k.length();
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }

    private static String keyWithDots(String str, int length) {
        StringBuilder sb = new StringBuilder(length);
        sb.append(Objects.toString(str));
        for (int i = sb.length(); i < length; i++) {
            sb.append(".");
        }
        return sb.toString();
    }
}
