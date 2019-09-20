package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import response.Pasta;
import java.util.List;

@RestController
public class PastaController
{
    @GetMapping("/pastas/")
    public List<Pasta> list()
    {
        return Storage.getLastTenPastas();
    }

    @PostMapping("/pastas/")
    private String add(Pasta pasta)
    {
        return Storage.addPasta(pasta);
    }

    @GetMapping("/pastas/{link}")
    public ResponseEntity get(@PathVariable String link)
    {
        Pasta pasta = Storage.getPasta(link);
        if (pasta == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return new ResponseEntity(pasta,HttpStatus.OK);
    }
}
