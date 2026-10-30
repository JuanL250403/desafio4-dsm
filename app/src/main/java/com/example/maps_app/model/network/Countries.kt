package com.example.maps_app.model.network

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    val data: DataContainer? = null
)

data class DataContainer(
    val objects: List<CountryObject> = emptyList(),
    val meta: Meta? = null
)

data class CountryObject(
    val names: Names? = null,
    val codes: Codes? = null,
    val capitals: List<Capital> = emptyList(),
    val flag: Flag? = null,
    val region: String? = null,
    val subregion: String? = null,
    val area: Area? = null,
    val assets: List<String> = emptyList(),
    val borders: List<String> = emptyList(),
    @SerializedName("calling_codes")
    val callingCodes: List<String> = emptyList(),
    val cars: Cars? = null,
    val classification: Classification? = null,
    val continents: List<String> = emptyList(),
    val coordinates: Coordinates? = null,
    val currencies: List<Currency> = emptyList(),
    val date: DateInfo? = null,
    val demonyms: Demonyms? = null,
    val descriptions: Descriptions? = null,
    val economy: Economy? = null,
    @SerializedName("government_type")
    val governmentType: String? = null,
    val landlocked: Boolean? = null,
    val languages: List<Language> = emptyList(),
    val leaders: List<Leader> = emptyList(),
    val links: Links? = null,
    val memberships: Memberships? = null,
    @SerializedName("number_format")
    val numberFormat: NumberFormat? = null,
    val parent: Parent? = null,
    val population: Long? = null,
    @SerializedName("postal_code")
    val postalCode: PostalCode? = null,
    val timezones: List<String> = emptyList(),
    val tlds: List<String> = emptyList(),
    val units: Units? = null,
    val uuid: String? = null,
    @SerializedName("_match")
    val match: List<Match> = emptyList(),
    @SerializedName("_meta")
    val metaData: MetaData? = null
)

data class Names(
    val alternates: List<String> = emptyList(),
    val common: String? = null,
    val native: Map<String, NativeName> = emptyMap(),
    val official: String? = null,
    val translations: Map<String, Translation> = emptyMap()
)

data class NativeName(
    val common: String? = null,
    val official: String? = null
)

data class Translation(
    val common: String? = null,
    val official: String? = null
)

data class Codes(
    @SerializedName("alpha_2")
    val alpha2: String? = null,
    @SerializedName("alpha_3")
    val alpha3: String? = null,
    val ccn3: String? = null,
    val cioc: String? = null,
    val fifa: String? = null,
    val fips: String? = null,
    val gec: String? = null
)

data class Capital(
    val attributes: Attributes? = null,
    val coordinates: Coordinates? = null,
    val name: String? = null
)

data class Attributes(
    val administrative: Boolean? = null,
    val constitutional: Boolean? = null,
    val executive: Boolean? = null,
    val judicial: Boolean? = null,
    val legislative: Boolean? = null,
    val primary: Boolean? = null
)

data class Coordinates(
    val lat: Double? = null,
    val lng: Double? = null
)

data class Flag(
    val colors: Colors? = null,
    val description: String? = null,
    val emoji: String? = null,
    @SerializedName("html_entity")
    val htmlEntity: String? = null,
    val unicode: String? = null,
    @SerializedName("url_png")
    val urlPng: String? = null,
    @SerializedName("url_svg")
    val urlSvg: String? = null
)

data class Colors(
    val dominant: String? = null,
    val palette: List<PaletteColor> = emptyList(),
    val prominent: String? = null,
    val swatches: Swatches? = null
)

data class PaletteColor(
    val hex: String? = null,
    val proportion: Double? = null
)

data class Swatches(
    @SerializedName("dark_muted")
    val darkMuted: String? = null,
    @SerializedName("dark_vibrant")
    val darkVibrant: String? = null,
    @SerializedName("light_muted")
    val lightMuted: String? = null,
    @SerializedName("light_vibrant")
    val lightVibrant: String? = null,
    val muted: String? = null,
    val vibrant: String? = null
)

data class Area(
    val kilometers: Double? = null,
    val miles: Double? = null
)

data class Cars(
    @SerializedName("driving_side")
    val drivingSide: String? = null,
    val signs: List<String> = emptyList()
)

data class Classification(
    val dependency: Boolean? = null,
    @SerializedName("dependency_type")
    val dependencyType: String? = null,
    val disputed: Boolean? = null,
    @SerializedName("iso_status")
    val isoStatus: String? = null,
    val sovereign: Boolean? = null,
    @SerializedName("un_member")
    val unMember: Boolean? = null,
    @SerializedName("un_observer")
    val unObserver: Boolean? = null
)

data class Currency(
    val code: String? = null,
    val name: String? = null,
    val symbol: String? = null
)

data class DateInfo(
    @SerializedName("academic_year_start")
    val academicYearStart: YearStart? = null,
    @SerializedName("fiscal_year_start")
    val fiscalYearStart: FiscalYearStart? = null,
    @SerializedName("start_of_week")
    val startOfWeek: String? = null
)

data class YearStart(
    val day: Int? = null,
    val month: Int? = null
)

data class FiscalYearStart(
    val corporate: Corporate? = null,
    val government: Government? = null,
    val personal: Personal? = null
)

data class Corporate(
    val basis: String? = null,
    val day: Int? = null,
    val month: Int? = null
)

data class Government(
    val day: Int? = null,
    val month: Int? = null
)

data class Personal(
    val day: Int? = null,
    val month: Int? = null
)

data class Demonyms(
    val eng: DemonymDetail? = null,
    val fra: DemonymDetail? = null
)

data class DemonymDetail(
    val f: String? = null,
    val m: String? = null
)

data class Descriptions(
    val long: String? = null,
    val short: String? = null
)

data class Economy(
    @SerializedName("gini_coefficient")
    val giniCoefficient: Map<String, Double> = emptyMap()
)

data class Language(
    val bcp47: String? = null,
    @SerializedName("iso639_1")
    val iso6391: String? = null,
    @SerializedName("iso639_2b")
    val iso6392b: String? = null,
    @SerializedName("iso639_2t")
    val iso6392t: String? = null,
    @SerializedName("iso639_3")
    val iso6393: String? = null,
    val name: String? = null,
    @SerializedName("native_name")
    val nativeName: String? = null
)

data class Leader(
    val message: String? = null,
    val sample: String? = null
)

data class Links(
    @SerializedName("google_maps")
    val googleMaps: String? = null,
    val official: String? = null,
    @SerializedName("open_street_maps")
    val openStreetMaps: String? = null,
    val wikipedia: String? = null
)

data class Memberships(
    @SerializedName("african_union")
    val africanUnion: Boolean? = null,
    @SerializedName("arab_league")
    val arabLeague: Boolean? = null,
    val asean: Boolean? = null,
    val brics: Boolean? = null,
    val commonwealth: Boolean? = null,
    val eu: Boolean? = null,
    val eurozone: Boolean? = null,
    val g20: Boolean? = null,
    val g7: Boolean? = null,
    val nato: Boolean? = null,
    val oecd: Boolean? = null,
    val opec: Boolean? = null,
    val schengen: Boolean? = null,
    val un: Boolean? = null
)

data class NumberFormat(
    @SerializedName("decimal_separator")
    val decimalSeparator: String? = null,
    @SerializedName("thousands_separator")
    val thousandsSeparator: String? = null
)

data class Parent(
    @SerializedName("alpha_2")
    val alpha2: String? = null,
    @SerializedName("alpha_3")
    val alpha3: String? = null
)

data class PostalCode(
    val format: String? = null,
    val regex: String? = null
)

data class Units(
    @SerializedName("measurement_system")
    val measurementSystem: String? = null,
    @SerializedName("temperature_scale")
    val temperatureScale: String? = null
)

data class Match(
    val path: String? = null,
    val value: String? = null
)

data class MetaData(
    @SerializedName("lastUpdatedTimestamp")
    val lastUpdatedTimestamp: Long? = null
)

data class Meta(
    val total: Int? = null,
    val count: Int? = null,
    val limit: Int? = null,
    val offset: Int? = null,
    val more: Boolean? = null,
    @SerializedName("request_id")
    val requestId: String? = null,
    val duration: Int? = null
)
