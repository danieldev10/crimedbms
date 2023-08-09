package dev.achonma.crimedbms.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dev.achonma.crimedbms.models.Crime;
import dev.achonma.crimedbms.models.CriminalRecord;
import dev.achonma.crimedbms.models.Prison;
import dev.achonma.crimedbms.models.State;
import dev.achonma.crimedbms.models.User;
import dev.achonma.crimedbms.services.CrimeService;
import dev.achonma.crimedbms.services.CriminalRecordService;
import dev.achonma.crimedbms.services.PrisonService;
import dev.achonma.crimedbms.services.StateService;
import dev.achonma.crimedbms.services.UserService;

@Controller
@RequestMapping("/")
public class CriminalRecordController {
    @Autowired
    private CriminalRecordService criminalRecordService;

    @Autowired
    private PrisonService prisonService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CrimeService crimeService;

    @Autowired
    UserService userService;

    @GetMapping("/createnewrecord")
    public String createRecordForm(Model model) {
        CriminalRecord criminalRecord = new CriminalRecord();
        List<Crime> crimeList = crimeService.get();
        List<Prison> prisonList = prisonService.get();
        List<State> stateList = stateService.get();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("criminalrecord", criminalRecord);
        model.addAttribute("prisons", prisonList);
        model.addAttribute("states", stateList);
        model.addAttribute("crimes", crimeList);
        return "createnewrecord";
    }

    @PostMapping("/")
    public String saveCriminalRecord(@ModelAttribute("criminalrecord") CriminalRecord criminalRecord, Model model,
            @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Prison prison = prisonService.get(criminalRecord.getPrison().getId());
        State state = stateService.get(criminalRecord.getState().getId());

        criminalRecord.setPhoto(fileName);
        criminalRecord.setPrison(prison);
        criminalRecord.setState(state);

        // Save the criminal record to get the generated ID
        CriminalRecord savedCriminalRecord = criminalRecordService.save(criminalRecord);
        Long id = savedCriminalRecord.getId();

        // Use the generated ID to create the upload directory
        String uploadDir = "./static/assets/img/photos/" + id;
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }

        return "redirect:/";
    }

    @GetMapping("/records/{id}")
    public String editRedordForm(@PathVariable Long id, Model model) {
        List<Prison> prisonList = prisonService.get();
        List<State> stateList = stateService.get();
        List<Crime> crimeList = crimeService.get();
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("crimes", crimeList);
        model.addAttribute("prisons", prisonList);
        model.addAttribute("states", stateList);
        model.addAttribute("criminalrecord", criminalRecordService.get(id));
        return "editrecord";
    }

    @PostMapping("/records/{id}")
    public String updateRecord(@PathVariable Long id, @ModelAttribute("criminalrecord") CriminalRecord criminalRecord,
            Model model) {
        CriminalRecord existingRecord = criminalRecordService.get(id);
        existingRecord.setFirst_name(criminalRecord.getFirst_name());
        existingRecord.setCase_number(criminalRecord.getCase_number());
        existingRecord.setCharges(criminalRecord.getCharges());
        existingRecord.setCourt(criminalRecord.getCourt());
        existingRecord.setCourt_date(criminalRecord.getCourt_date());
        existingRecord.setDate_of_arrest(criminalRecord.getDate_of_arrest());
        existingRecord.setDate_of_offense(criminalRecord.getDate_of_offense());
        existingRecord.setLocation_of_offence(criminalRecord.getLocation_of_offence());
        existingRecord.setLast_name(criminalRecord.getLast_name());
        existingRecord.setCrime(criminalRecord.getCrime());
        existingRecord.setPunishment_start_date(criminalRecord.getPunishment_start_date());
        existingRecord.setPunishment_period(criminalRecord.getPunishment_period());

        // Retrieve the Prison and State objects based on their IDs
        Prison prison = prisonService.get(criminalRecord.getPrison().getId());
        State state = stateService.get(criminalRecord.getState().getId());

        // Set the Prison and State objects for the existing CriminalRecord
        existingRecord.setPrison(prison);
        existingRecord.setState(state);

        criminalRecordService.save(existingRecord);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deleteCriminalRecord(@PathVariable Long id) {
        criminalRecordService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String viewRecordDetails(@PathVariable Long id, Model model) {
        CriminalRecord criminalRecord = criminalRecordService.get(id);
        User user = userService.getCurrentUser();
        model.addAttribute("firstName", user.getFirstname());
        model.addAttribute("lastName", user.getLastname());
        model.addAttribute("criminalrecord", criminalRecord);
        return "viewrecorddetail"; // Return the view_record.html page
    }
}