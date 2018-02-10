package my.cipher.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import my.cipher.controller.forms.CipherForm;
import my.cipher.controller.forms.StringResponse;
import my.cipher.persistence.model.CipherEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import my.cipher.persistence.repo.CipherEntryRepository;
import my.ciphers.types.CaesarShiftCipher;
import my.ciphers.types.Cipher;
import my.ciphers.types.MonoAlphabeticCipher;
import my.ciphers.types.VigenerCipher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SimpleController {

    private static final Logger LOG = Logger.getLogger(SimpleController.class.getName());

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    CipherEntryRepository repo;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String create(@ModelAttribute CipherForm cipherForm, RedirectAttributes redirect) throws IOException {

        Cipher cipher = determineCipherType(cipherForm);

        if (cipher == null) {
            redirect.addFlashAttribute("globalMessageError", "Invalid cipher type.");
        }

        final CipherEntry cipherEntry = new CipherEntry();
        final String encodedValue = cipher.encode(cipherForm.getPlainText());
        cipherEntry.setText(encodedValue);
        cipherEntry.setType(cipherForm.getCipherType());
        cipherEntry.setKey(String.valueOf(cipherForm.getKey()));
        repo.save(cipherEntry);
        redirect.addFlashAttribute("globalMessage", "New Cipher text was save successfully.");

        redirect.addAttribute("", "");

        return "redirect:/";
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public String retrieve(RedirectAttributes redirect) throws IOException {
        final Optional<CipherEntry> lastEntry = repo.findFirstByOrderByIdDesc();
        if (lastEntry.isPresent()){
            LOG.info(String.format("RETRIEVED[%s]", lastEntry.toString()));
            redirect.addFlashAttribute("lastEntry", lastEntry.get());
        }else{
            redirect.addFlashAttribute("globalMessageError", "No recent saved entry. Database is empty");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/bruteForce", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> bruteForce(@RequestBody CipherForm cipherForm, RedirectAttributes redirect) throws IOException {
        Cipher cipher = determineCipherType(cipherForm);
        return ResponseEntity.ok(new StringResponse(cipher.decode(cipherForm.getPlainText())));
    }

    private Cipher determineCipherType(CipherForm cipherForm) throws NumberFormatException {
        Cipher cipher = null;
        if ("CAESAR-SHIFT".equals(cipherForm.getCipherType())) {
            cipher = new CaesarShiftCipher(Integer.valueOf(cipherForm.getKey()));
        }
        if ("VIGENER".equals(cipherForm.getCipherType())) {
            cipher = new VigenerCipher(cipherForm.getKey());
        }
        if ("CAESAR-MONO".equals(cipherForm.getCipherType())) {
            cipher = new MonoAlphabeticCipher();
        }
        return cipher;
    }

}
