package org.hackathon.apis.service;

import org.hackathon.apis.dto.LocationDto;
import org.hackathon.apis.model.DevDto;
import org.springframework.stereotype.Component;

@Component
public class LocationService {

    public void goToOpenSpace(DevDto devDto) {
        LocationDto openSpace = new LocationDto("Open space", "");
        devDto.setLieuActuel(openSpace);
    }

    public void goToCoffeeArea(DevDto devDto) {
        LocationDto coffeeArea = new LocationDto("Espace café", "");
        devDto.setLieuActuel(coffeeArea);
    }

    public void goToMeetingRoom(DevDto devDto) {
        LocationDto meetingRoom = new LocationDto("Salle de réunion", "");
        devDto.setLieuActuel(meetingRoom);
    }

    /**
     * For remote working
     */
    public void goHome(DevDto devDto) {
        LocationDto home = new LocationDto("Maison", "");
        devDto.setLieuActuel(home);
    }
}
