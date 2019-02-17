package com.dkm.gdpartifact;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class AgGdpController
{
    public final AcGDPRepository gdprepos;
    public final RabbitTemplate rt;

    public AgGdpController(AcGDPRepository gdprepos, RabbitTemplate rt)
    {
        this.gdprepos = gdprepos;
        this.rt = rt;
    }

// /names - return using the JSON format all of the countries alphabetized by name
    @GetMapping("/names")
    public List<AdGdp> allByName()
    {
        log.info("\n****** names *******\n");
        return gdprepos
                .findAll()
                .stream()
                .sorted((e1, e2) -> e1.getCountry().compareToIgnoreCase(e2.getCountry()))
                .collect(Collectors.toList());

    }

// /economy - return using the JSON format all of the countries sorted from most to least GDP
    @GetMapping("economy")
    public List<AdGdp> allByGdp()
    {
        log.info("\n****** gdp *******\n");
        return gdprepos
                .findAll()
                .stream()
                .sorted((e1, e2) -> e2.getGdp().compareTo(e1.getGdp()))
                .collect(Collectors.toList());
    }

// /total - return the sum of all GDPs using the JSON format with country name being returned as Total
    @GetMapping("/total")
    public ObjectNode sumGdp()
    {
        List<AdGdp> languages = gdprepos.findAll();

        //Long needs L
        Long total = 0L;

        for (AdGdp l : languages)
        {
            total += l.getGdp();
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode totalPops = mapper.createObjectNode();
        totalPops.put("id", 0);
        totalPops.put("country", "total");
        totalPops.put("gdp", total);

        AfGdpLog message = new AfGdpLog("Checked total GDP");
        rt.convertAndSend(AaGdpNameApplication.QUEUE_NAME, message.toString());
        log.info("\n\n============ Message Sent ==============\n\n");

        return  totalPops;
    }


// /gdp/{country name} - return using the JSON format the record for that country. Must be spelled as in the data!
//        Log that someone looked up this country
    @GetMapping("gdp/{id}")
    public AdGdp findOneByCountry(@PathVariable Long id)
    {
//        log.info("Someone looked up: " + gdprepos.findById(id));
        return gdprepos.findById(id)
                .orElseThrow(() -> new AeCountryNotFoundException(""));
    }

    @GetMapping("/total/{country}")
    public AdGdp findOne(@PathVariable String country) {
        for (AdGdp g : gdprepos.findAll()) {
            if (g.getCountry().equalsIgnoreCase(country)) {
                log.info("\n\n****** Someone looked up: " + country +" ******\n\n");
                return g;
            }
        }
        throw new AeCountryNotFoundException(country);
    }

// /gdp - loads the data from the provided JSON file
    @PostMapping("/gdp")
    public List<AdGdp> addAll(@RequestBody List<AdGdp> newGdp)
    {
        return gdprepos.saveAll(newGdp);
    }

}




