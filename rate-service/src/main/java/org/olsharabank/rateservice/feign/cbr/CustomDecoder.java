package org.olsharabank.rateservice.feign.cbr;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import feign.Response;
import feign.codec.Decoder;
import org.olsharabank.rateservice.dto.ValCursDto;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

public class CustomDecoder implements Decoder {
    private final XmlMapper xmlMapper;

    public CustomDecoder() {
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        Charset charset = Charset.forName("Windows-1251");

        try (Reader reader = new InputStreamReader(response.body().asInputStream(), charset)) {
            return xmlMapper.readValue(reader, ValCursDto.class);
        }
    }
}
