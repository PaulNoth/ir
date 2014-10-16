package eu.pidanic.ir.parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface Parser
{
    Map<String, String> parse(File file) throws IOException;
}
