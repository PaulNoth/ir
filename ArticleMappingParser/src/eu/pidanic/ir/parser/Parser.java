package eu.pidanic.ir.parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface Parser
{
    Map<String, String> parse(File file) throws IOException;

    void parseToFile(File from, File to) throws IOException;

    Map<String, String> parse(String path) throws IOException;

    void parseToFile(String pathFrom, String pathTo) throws IOException;
}
