package com.example.uade.tpo.demo.controllers;

public class viniloController {
    
}

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController

@RequestMapping("/api/vinilos")
public class viniloController {
    
    /* @Autowired
    private VinylService vinylService;
*/
    
    @GetMapping
    public List<Vinyl> getAllVinyls() {
        return vinylService.getAllVinyls();
    }

    @GetMapping("/{id}")
    public Vinyl getVinylById(@PathVariable Long id) {
        return vinylService.getVinylById(id);
    }

    @PostMapping
    public Vinyl createVinyl(@RequestBody Vinyl vinyl) {
        return vinylService.saveVinyl(vinyl);
    }

    @DeleteMapping("/{id}")
    public void deleteVinyl(@PathVariable Long id) {
        vinylService.deleteVinyl(id);
    }
}
