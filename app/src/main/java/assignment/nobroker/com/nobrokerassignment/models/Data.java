package assignment.nobroker.com.nobrokerassignment.models;

import java.io.Serializable;
import java.util.ArrayList;

import android.arch.persistence.room.Entity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import assignment.nobroker.com.nobrokerassignment.BR;
import assignment.nobroker.com.nobrokerassignment.R;
import assignment.nobroker.com.nobrokerassignment.ui.PropertiesAdapter;

@Entity
public class Data extends BaseObservable implements Serializable
{
    public static DiffCallback<Data> DIFF_CALLBACK = new DiffCallback<Data>() {
        @Override
        public boolean areItemsTheSame(Data oldItem,Data newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(Data oldItem,Data newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Data user = (Data) obj;

        return user.getId().equals(this.getId());
    }

    private int propertyAge;

    public int getPropertyAge() { return this.propertyAge; }

    public void setPropertyAge(int propertyAge) { this.propertyAge = propertyAge; }

    private String parking;

    public String getParking() { return this.parking; }

    public void setParking(String parking) { this.parking = parking; }

    private String parkingDesc;

    public String getParkingDesc() { return this.parkingDesc; }

    public void setParkingDesc(String parkingDesc) { this.parkingDesc = parkingDesc; }

    private boolean shortlistedByLoggedInUser;

    public boolean getShortlistedByLoggedInUser() { return this.shortlistedByLoggedInUser; }

    public void setShortlistedByLoggedInUser(boolean shortlistedByLoggedInUser) { this.shortlistedByLoggedInUser = shortlistedByLoggedInUser; }

    private long lastUpdateDate;

    public long getLastUpdateDate() { return this.lastUpdateDate; }

    public void setLastUpdateDate(long lastUpdateDate) { this.lastUpdateDate = lastUpdateDate; }

    private int balconies;

    public int getBalconies() { return this.balconies; }

    public void setBalconies(int balconies) { this.balconies = balconies; }

    private String furnishingDesc;

    public String getFurnishingDesc() { return this.furnishingDesc; }

    public void setFurnishingDesc(String furnishingDesc) { this.furnishingDesc = furnishingDesc; }

    private int cupBoard;

    public int getCupBoard() { return this.cupBoard; }

    public void setCupBoard(int cupBoard) { this.cupBoard = cupBoard; }

    private boolean negotiable;

    public boolean getNegotiable() { return this.negotiable; }

    public void setNegotiable(boolean negotiable) { this.negotiable = negotiable; }

    private String type;

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    private String ownerId;

    public String getOwnerId() { return this.ownerId; }

    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    private int rent;

    @Bindable
    public int getRent() { return this.rent; }

    public void setRent(int rent) {
        this.rent = rent;
    }

    private long availableFrom;

    public long getAvailableFrom() { return this.availableFrom; }

    public void setAvailableFrom(long availableFrom) { this.availableFrom = availableFrom; }

    private ArrayList<Photo> photos;

    public ArrayList<Photo> getPhotos() { return this.photos; }

    public void setPhotos(ArrayList<Photo> photos) { this.photos = photos; }

    private int propertySize;

    public int getPropertySize() { return this.propertySize; }

    public void setPropertySize(int propertySize) { this.propertySize = propertySize; }

    private boolean loanAvailable;

    public boolean getLoanAvailable() { return this.loanAvailable; }

    public void setLoanAvailable(boolean loanAvailable) { this.loanAvailable = loanAvailable; }

    private Score score;

    public Score getScore() { return this.score; }

    public void setScore(Score score) { this.score = score; }

    private String accomodationTypeDesc;

    public String getAccomodationTypeDesc() { return this.accomodationTypeDesc; }

    public void setAccomodationTypeDesc(String accomodationTypeDesc) { this.accomodationTypeDesc = accomodationTypeDesc; }

    private String propertyCode;

    public String getPropertyCode() { return this.propertyCode; }

    public void setPropertyCode(String propertyCode) { this.propertyCode = propertyCode; }

    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    private String localityId;

    public String getLocalityId() { return this.localityId; }

    public void setLocalityId(String localityId) { this.localityId = localityId; }

    private String adminEvent;

    public String getAdminEvent() { return this.adminEvent; }

    public void setAdminEvent(String adminEvent) { this.adminEvent = adminEvent; }

    private int bathroom;

    public int getBathroom() { return this.bathroom; }

    public void setBathroom(int bathroom) { this.bathroom = bathroom; }

    private String propertyTitle;

    @Bindable
    public String getPropertyTitle() { return this.propertyTitle; }

    public void setPropertyTitle(String propertyTitle) { this.propertyTitle = propertyTitle; }

    private String imageUrl;

    public void setImageUrl(String imageUrl){
        this.imageUrl=imageUrl;
    }

    @Bindable
    public String getImageUrl() {
        Log.e(Data.class.getSimpleName(),"Inside class image url is : "+imageUrl);
        return this.imageUrl;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(imageUrl!=null)
            Picasso.with(view.getContext())
                    .load(get_image_url(imageUrl))
                    .fit().centerCrop().into(view);
        else
            Picasso.with(view.getContext())
                    .load("https://images.nobroker.in/static/img/nopic_1bhk.jpg")
                    .fit().centerCrop().into(view);
    }

    public static String get_image_url(String image_url){
        if(image_url!=null && image_url.contains("_")){
            String folder_directory=image_url.substring(0,image_url.indexOf("_"));
            String final_url="http://d3snwcirvb4r88.cloudfront.net/images/"+folder_directory+"/"+image_url;
            return final_url;
        }
        else{
            return null;
        }
    }

    private double longitude;

    public double getLongitude() { return this.longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    private String powerBackup;

    public String getPowerBackup() { return this.powerBackup; }

    public void setPowerBackup(String powerBackup) { this.powerBackup = powerBackup; }

    private long dateOnly;

    public long getDateOnly() { return this.dateOnly; }

    public void setDateOnly(long dateOnly) { this.dateOnly = dateOnly; }

    private String inactiveReason;

    public String getInactiveReason() { return this.inactiveReason; }

    public void setInactiveReason(String inactiveReason) { this.inactiveReason = inactiveReason; }

    private String locality;

    public String getLocality() { return this.locality; }

    public void setLocality(String locality) { this.locality = locality; }

    private boolean active;

    public boolean getActive() { return this.active; }

    public void setActive(boolean active) { this.active = active; }

    private long creationDate;

    public long getCreationDate() { return this.creationDate; }

    public void setCreationDate(long creationDate) { this.creationDate = creationDate; }

    private boolean swimmingPool;

    public boolean getSwimmingPool() { return this.swimmingPool; }

    public void setSwimmingPool(boolean swimmingPool) { this.swimmingPool = swimmingPool; }

    private String accomodationType;

    public String getAccomodationType() { return this.accomodationType; }

    public void setAccomodationType(String accomodationType) { this.accomodationType = accomodationType; }

    private String waterSupply;

    public String getWaterSupply() { return this.waterSupply; }

    public void setWaterSupply(String waterSupply) { this.waterSupply = waterSupply; }

    private boolean accurateLocation;

    public boolean getAccurateLocation() { return this.accurateLocation; }

    public void setAccurateLocation(boolean accurateLocation) { this.accurateLocation = accurateLocation; }

    private int pinCode;

    public int getPinCode() { return this.pinCode; }

    public void setPinCode(int pinCode) { this.pinCode = pinCode; }

    private int totalFloor;

    public int getTotalFloor() { return this.totalFloor; }

    public void setTotalFloor(int totalFloor) { this.totalFloor = totalFloor; }

    private boolean lift;

    public boolean getLift() { return this.lift; }

    public void setLift(boolean lift) { this.lift = lift; }

    private int deposit;

    public int getDeposit() { return this.deposit; }

    public void setDeposit(int deposit) { this.deposit = deposit; }

    private boolean gym;

    public boolean getGym() { return this.gym; }

    public void setGym(boolean gym) { this.gym = gym; }

    private String detailUrl;

    public String getDetailUrl() { return this.detailUrl; }

    public void setDetailUrl(String detailUrl) { this.detailUrl = detailUrl; }

    private long activationDate;

    public long getActivationDate() { return this.activationDate; }

    public void setActivationDate(long activationDate) { this.activationDate = activationDate; }

    private String facingDesc;

    public String getFacingDesc() { return this.facingDesc; }

    public void setFacingDesc(String facingDesc) { this.facingDesc = facingDesc; }

    private String furnishing;

    @Bindable
    public String getFurnishing() { return this.furnishing; }

    public void setFurnishing(String furnishing) { this.furnishing = furnishing; }

    private String amenities;

    public String getAmenities() { return this.amenities; }

    public void setAmenities(String amenities) { this.amenities = amenities; }

    private boolean photoAvailable;

    public boolean getPhotoAvailable() { return this.photoAvailable; }

    public void setPhotoAvailable(boolean photoAvailable) { this.photoAvailable = photoAvailable; }

    private String typeDesc;

    public String getTypeDesc() { return this.typeDesc; }

    public void setTypeDesc(String typeDesc) { this.typeDesc = typeDesc; }

    private String city;

    public String getCity() { return this.city; }

    public void setCity(String city) { this.city = city; }

    private String shortUrl;

    public String getShortUrl() { return this.shortUrl; }

    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }

    private double latitude;

    public double getLatitude() { return this.latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    private String description;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    private String facing;

    public String getFacing() { return this.facing; }

    public void setFacing(String facing) { this.facing = facing; }

    private String title;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    private String nbLocality;

    public String getNbLocality() { return this.nbLocality; }

    public void setNbLocality(String nbLocality) { this.nbLocality = nbLocality; }

    private String leaseType;

    @Bindable
    public String getLeaseType() { return this.leaseType; }

    public void setLeaseType(String leaseType) { this.leaseType = leaseType; }

    private String society;

    public String getSociety() { return this.society; }

    public void setSociety(String society) { this.society = society; }

    private String ownerName;

    public String getOwnerName() { return this.ownerName; }

    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    private String street;

    public String getStreet() { return this.street; }

    public void setStreet(String street) { this.street = street; }

    private String managed;

    public String getManaged() { return this.managed; }

    public void setManaged(String managed) { this.managed = managed; }

    private String propertyType;

    @Bindable
    public String getPropertyType() { return this.propertyType; }

    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    private String secondaryTitle;

    @Bindable
    public String getSecondaryTitle() { return this.secondaryTitle; }

    public void setSecondaryTitle(String secondaryTitle) { this.secondaryTitle = secondaryTitle; }

    private int floor;

    public int getFloor() { return this.floor; }

    public void setFloor(int floor) { this.floor = floor; }

    private long lastActivationDate;

    public long getLastActivationDate() { return this.lastActivationDate; }

    public void setLastActivationDate(long lastActivationDate) { this.lastActivationDate = lastActivationDate; }

    private boolean forLease;

    public boolean getForLease() { return this.forLease; }

    public void setForLease(boolean forLease) { this.forLease = forLease; }

    private String inactiveReasonDesc;

    public String getInactiveReasonDesc() { return this.inactiveReasonDesc; }

    public void setInactiveReasonDesc(String inactiveReasonDesc) { this.inactiveReasonDesc = inactiveReasonDesc; }

    private boolean sponsored;

    public boolean getSponsored() { return this.sponsored; }

    public void setSponsored(boolean sponsored) { this.sponsored = sponsored; }

    private String buildingId;

    public String getBuildingId() { return this.buildingId; }

    public void setBuildingId(String buildingId) { this.buildingId = buildingId; }

    private String tenantTypeDesc;

    public String getTenantTypeDesc() { return this.tenantTypeDesc; }

    public void setTenantTypeDesc(String tenantTypeDesc) { this.tenantTypeDesc = tenantTypeDesc; }

    private AmenitiesMap amenitiesMap;

    public AmenitiesMap getAmenitiesMap() { return this.amenitiesMap; }

    public void setAmenitiesMap(AmenitiesMap amenitiesMap) { this.amenitiesMap = amenitiesMap; }

    private String location;

    public String getLocation() { return this.location; }

    public void setLocation(String location) { this.location = location; }

    private boolean sharedAccomodation;

    public boolean getSharedAccomodation() { return this.sharedAccomodation; }

    public void setSharedAccomodation(boolean sharedAccomodation) { this.sharedAccomodation = sharedAccomodation; }

    private String buildingType;

    public String getBuildingType() { return this.buildingType; }

    public void setBuildingType(String buildingType) { this.buildingType = buildingType; }

    @Override
    public String toString() {
        return "Data{" +
                "propertyAge=" + propertyAge +
                ", parking='" + parking + '\'' +
                ", parkingDesc='" + parkingDesc + '\'' +
                ", shortlistedByLoggedInUser=" + shortlistedByLoggedInUser +
                ", lastUpdateDate=" + lastUpdateDate +
                ", balconies=" + balconies +
                ", furnishingDesc='" + furnishingDesc + '\'' +
                ", cupBoard=" + cupBoard +
                ", negotiable=" + negotiable +
                ", type='" + type + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", rent=" + rent +
                ", availableFrom=" + availableFrom +
                ", photos=" + photos +
                ", propertySize=" + propertySize +
                ", loanAvailable=" + loanAvailable +
                ", score=" + score +
                ", accomodationTypeDesc='" + accomodationTypeDesc + '\'' +
                ", propertyCode='" + propertyCode + '\'' +
                ", id='" + id + '\'' +
                ", localityId='" + localityId + '\'' +
                ", adminEvent='" + adminEvent + '\'' +
                ", bathroom=" + bathroom +
                ", propertyTitle='" + propertyTitle + '\'' +
                ", longitude=" + longitude +
                ", powerBackup='" + powerBackup + '\'' +
                ", dateOnly=" + dateOnly +
                ", inactiveReason='" + inactiveReason + '\'' +
                ", locality='" + locality + '\'' +
                ", active=" + active +
                ", creationDate=" + creationDate +
                ", swimmingPool=" + swimmingPool +
                ", accomodationType='" + accomodationType + '\'' +
                ", waterSupply='" + waterSupply + '\'' +
                ", accurateLocation=" + accurateLocation +
                ", pinCode=" + pinCode +
                ", totalFloor=" + totalFloor +
                ", lift=" + lift +
                ", deposit=" + deposit +
                ", gym=" + gym +
                ", detailUrl='" + detailUrl + '\'' +
                ", activationDate=" + activationDate +
                ", facingDesc='" + facingDesc + '\'' +
                ", furnishing='" + furnishing + '\'' +
                ", amenities='" + amenities + '\'' +
                ", photoAvailable=" + photoAvailable +
                ", typeDesc='" + typeDesc + '\'' +
                ", city='" + city + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", latitude=" + latitude +
                ", description='" + description + '\'' +
                ", facing='" + facing + '\'' +
                ", title='" + title + '\'' +
                ", nbLocality='" + nbLocality + '\'' +
                ", leaseType='" + leaseType + '\'' +
                ", society='" + society + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", street='" + street + '\'' +
                ", managed='" + managed + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", secondaryTitle='" + secondaryTitle + '\'' +
                ", floor=" + floor +
                ", lastActivationDate=" + lastActivationDate +
                ", forLease=" + forLease +
                ", inactiveReasonDesc='" + inactiveReasonDesc + '\'' +
                ", sponsored=" + sponsored +
                ", buildingId='" + buildingId + '\'' +
                ", tenantTypeDesc='" + tenantTypeDesc + '\'' +
                ", amenitiesMap=" + amenitiesMap +
                ", location='" + location + '\'' +
                ", sharedAccomodation=" + sharedAccomodation +
                ", buildingType='" + buildingType + '\'' +
                '}';
    }
}