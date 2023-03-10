package org.example;

import io.numaproj.numaflow.function.FunctionServer;
import io.numaproj.numaflow.function.Message;
import io.numaproj.numaflow.function.map.MapFunc;
import io.numaproj.numaflow.function.v1.Udfunction;

import java.io.IOException;

public class Forward {
    private static Message[] process(String key, Udfunction.Datum data) {
        int value = Integer.parseInt(new String(data.getValue().toByteArray()));
        if (value % 2 == 0) {
            return new Message[]{Message.to("even", data.getValue().toByteArray())};
        }
        return new Message[]{Message.to("odd", data.getValue().toByteArray())};
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Forward invoked");
        new FunctionServer().registerMapper(new MapFunc(Forward::process)).start();
    }
}