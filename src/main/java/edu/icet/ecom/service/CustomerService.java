package edu.icet.ecom.service;

import edu.icet.ecom.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
//@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final List<Customer> customers = Collections.synchronizedList(new ArrayList<>());

    public CustomerService() {
        customers.add(new Customer("Sasmitha", "1234567890"));
        customers.add(new Customer("Alice", "0987654321"));
        customers.add(new Customer("Customer1", "9123456780"));
        customers.add(new Customer("Customer2", "9234567891"));
        customers.add(new Customer("Customer3", "9345678902"));
        customers.add(new Customer("Customer4", "9456789013"));
        customers.add(new Customer("Customer5", "9567890124"));
        customers.add(new Customer("Customer6", "9678901235"));
        customers.add(new Customer("Customer7", "9789012346"));
        customers.add(new Customer("Customer8", "9890123457"));
        customers.add(new Customer("Customer9", "9901234568"));
        customers.add(new Customer("Customer10", "9012345679"));
        customers.add(new Customer("Customer11", "9123456790"));
        customers.add(new Customer("Customer12", "9234567801"));
        customers.add(new Customer("Customer13", "9345678912"));
        customers.add(new Customer("Customer14", "9456789023"));
        customers.add(new Customer("Customer15", "9567890134"));
        customers.add(new Customer("Customer16", "9678901245"));
        customers.add(new Customer("Customer17", "9789012356"));
        customers.add(new Customer("Customer18", "9890123467"));
        customers.add(new Customer("Customer19", "9901234578"));
        customers.add(new Customer("Customer20", "9012345689"));
        customers.add(new Customer("Customer21", "9123456791"));
        customers.add(new Customer("Customer22", "9234567802"));
        customers.add(new Customer("Customer23", "9345678913"));
        customers.add(new Customer("Customer24", "9456789024"));
        customers.add(new Customer("Customer25", "9567890135"));
        customers.add(new Customer("Customer26", "9678901246"));
        customers.add(new Customer("Customer27", "9789012357"));
        customers.add(new Customer("Customer28", "9890123468"));
        customers.add(new Customer("Customer29", "9901234579"));
        customers.add(new Customer("Customer30", "9012345690"));
        customers.add(new Customer("Customer31", "9123456792"));
        customers.add(new Customer("Customer32", "9234567803"));
        customers.add(new Customer("Customer33", "9345678914"));
        customers.add(new Customer("Customer34", "9456789025"));
        customers.add(new Customer("Customer35", "9567890136"));
        customers.add(new Customer("Customer36", "9678901247"));
        customers.add(new Customer("Customer37", "9789012358"));
        customers.add(new Customer("Customer38", "9890123469"));
        customers.add(new Customer("Customer39", "9901234580"));
        customers.add(new Customer("Customer40", "9012345691"));
        customers.add(new Customer("Customer41", "9123456793"));
        customers.add(new Customer("Customer42", "9234567804"));
        customers.add(new Customer("Customer43", "9345678915"));
        customers.add(new Customer("Customer44", "9456789026"));
        customers.add(new Customer("Customer45", "9567890137"));
        customers.add(new Customer("Customer46", "9678901248"));
        customers.add(new Customer("Customer47", "9789012359"));
        customers.add(new Customer("Customer48", "9890123470"));
        customers.add(new Customer("Customer49", "9901234581"));
        customers.add(new Customer("Customer50", "9012345692"));
        customers.add(new Customer("Customer51", "9123456794"));
        customers.add(new Customer("Customer52", "9234567805"));
        customers.add(new Customer("Customer53", "9345678916"));
        customers.add(new Customer("Customer54", "9456789027"));
        customers.add(new Customer("Customer55", "9567890138"));
        customers.add(new Customer("Customer56", "9678901249"));
        customers.add(new Customer("Customer57", "9789012360"));
        customers.add(new Customer("Customer58", "9890123471"));
        customers.add(new Customer("Customer59", "9901234582"));
        customers.add(new Customer("Customer60", "9012345693"));
        customers.add(new Customer("Customer61", "9123456795"));
        customers.add(new Customer("Customer62", "9234567806"));
        customers.add(new Customer("Customer63", "9345678917"));
        customers.add(new Customer("Customer64", "9456789028"));
        customers.add(new Customer("Customer65", "9567890139"));
        customers.add(new Customer("Customer66", "9678901250"));
        customers.add(new Customer("Customer67", "9789012361"));
        customers.add(new Customer("Customer68", "9890123472"));
        customers.add(new Customer("Customer69", "9901234583"));
        customers.add(new Customer("Customer70", "9012345694"));
        customers.add(new Customer("Customer71", "9123456796"));
        customers.add(new Customer("Customer72", "9234567807"));
        customers.add(new Customer("Customer73", "9345678918"));
        customers.add(new Customer("Customer74", "9456789029"));
        customers.add(new Customer("Customer75", "9567890140"));
        customers.add(new Customer("Customer76", "9678901251"));
        customers.add(new Customer("Customer77", "9789012362"));
        customers.add(new Customer("Customer78", "9890123473"));
        customers.add(new Customer("Customer79", "9901234584"));
        customers.add(new Customer("Customer80", "9012345695"));
        customers.add(new Customer("Customer81", "9123456797"));
        customers.add(new Customer("Customer82", "9234567808"));
        customers.add(new Customer("Customer83", "9345678919"));
        customers.add(new Customer("Customer84", "9456789030"));
        customers.add(new Customer("Customer85", "9567890141"));
        customers.add(new Customer("Customer86", "9678901252"));
        customers.add(new Customer("Customer87", "9789012363"));
        customers.add(new Customer("Customer88", "9890123474"));
        customers.add(new Customer("Customer89", "9901234585"));
        customers.add(new Customer("Customer90", "9012345696"));
        customers.add(new Customer("Customer91", "9123456798"));
        customers.add(new Customer("Customer92", "9234567809"));
        customers.add(new Customer("Customer93", "9345678920"));
        customers.add(new Customer("Customer94", "9456789031"));
        customers.add(new Customer("Customer95", "9567890142"));
        customers.add(new Customer("Customer96", "9678901253"));
        customers.add(new Customer("Customer97", "9789012364"));
        customers.add(new Customer("Customer98", "9890123475"));
        customers.add(new Customer("Customer99", "9901234586"));
        customers.add(new Customer("Customer100", "9012345697"));

    }

    public List<Customer> getCustomers() {
        synchronized (customers) {
            return new ArrayList<>(customers);
        }
    }

    // Runs every Dec 25 at 09:00 server timezone
//    @Scheduled(cron = "0 0 9 25 12 *")
    @Scheduled(cron = "0 42 15 22 12 *", zone = "Asia/Colombo")
    public void sendChristmasMessages() {
        String message = "Merry Christmas";
        synchronized (customers) {
            for (Customer c : customers) {
                log.info("Sending '{}' to {} ({})", message, c.getName(), c.getPhoneNumber());
            }
        }
    }

    // Runs every Jan 1 at 09:00 server timezone
    @Scheduled(cron = "0 0 9 1 1 *")
    public void sendNewYearMessages() {
        String message = "Happy New Year";
        synchronized (customers) {
            for (Customer c : customers) {
                log.info("Sending '{}' to {} ({})", message, c.getName(), c.getPhoneNumber());
            }
        }
    }
}
