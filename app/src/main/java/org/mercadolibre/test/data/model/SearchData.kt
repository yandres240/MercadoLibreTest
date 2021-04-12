package org.mercadolibre.test.data.model

import kotlinx.android.parcel.Parcelize
import java.util.*

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
class Paging {
    var total = 0
    var primary_results = 0
    var offset = 0
    var limit = 0
}

class Ratings {
    var negative = 0.0
    var positive = 0.0
    var neutral = 0.0
}

class Transactions {
    var total = 0
    var canceled = 0
    var period: String? = null
    var ratings: Ratings? = null
    var completed = 0
}

class Excluded {
    var real_rate = 0.0
    var real_value = 0
}

class Claims {
    var rate = 0.0
    var value = 0
    var period: String? = null
    var excluded: Excluded? = null
}

class DelayedHandlingTime {
    var rate = 0.0
    var value = 0
    var period: String? = null
    var excluded: Excluded? = null
}

class Sales {
    var period: String? = null
    var completed = 0
}

class Cancellations {
    var rate = 0.0
    var value = 0
    var period: String? = null
    var excluded: Excluded? = null
}

class Metrics {
    var claims: Claims? = null
    var delayed_handling_time: DelayedHandlingTime? = null
    var sales: Sales? = null
    var cancellations: Cancellations? = null
}

class SellerReputation {
    var transactions: Transactions? = null
    var power_seller_status: String? = null
    var metrics: Metrics? = null
    var level_id: String? = null
    var protection_end_date: Date? = null
    var real_level: String? = null
}

class Eshop {
    var nick_name: String? = null
    var eshop_rubro: Any? = null
    var eshop_id = 0
    var eshop_locations: List<Any>? = null
    var site_id: String? = null
    var eshop_logo_url: String? = null
    var eshop_status_id = 0
    var seller = 0
    var eshop_experience = 0
}

class Seller {
    var id = 0
    var permalink: String? = null
    var registration_date: Date? = null
    var car_dealer = false
    var real_estate_agency = false
    var tags: List<String>? = null
    var seller_reputation: SellerReputation? = null
    var eshop: Eshop? = null
}

class Conditions {
    var context_restrictions: List<Any>? = null
    var start_time: Date? = null
    var end_time: Date? = null
    var eligible = false
}

class Metadata {
    var campaign_id: String? = null
    var promotion_id: String? = null
    var promotion_type: String? = null
}

class Price {
    var id: String? = null
    var type: String? = null
    var conditions: Conditions? = null
    var amount = 0.0
    var regular_amount = 0
    var currency_id: String? = null
    var exchange_rate_context: String? = null
    var metadata: Metadata? = null
    var last_updated: Date? = null
    var prices: List<Price>? = null
    var presentation: Presentation? = null
    var payment_method_prices: List<Any>? = null
    var reference_prices: List<Any>? = null
    var purchase_discounts: List<Any>? = null
}

class Presentation {
    var display_currency: String? = null
}

class Installments {
    var quantity = 0
    var amount = 0.0
    var rate = 0.0
    var currency_id: String? = null
}

class Address {
    var state_id: String? = null
    var state_name: String? = null
    var city_id: String? = null
    var city_name: String? = null
}

class Shipping {
    var free_shipping = false
    var mode: String? = null
    var tags: List<String>? = null
    var logistic_type: String? = null
    var store_pick_up = false
}

class Country {
    var id: String? = null
    var name: String? = null
}

class State {
    var id: String? = null
    var name: String? = null
}

class City {
    var id: String? = null
    var name: String? = null
}

class SellerAddress {
    var id: String? = null
    var comment: String? = null
    var address_line: String? = null
    var zip_code: String? = null
    var country: Country? = null
    var state: State? = null
    var city: City? = null
    var latitude: String? = null
    var longitude: String? = null
}

class ValueStruct {
    var number = 0.0
    var unit: String? = null
}

class Struct {
    var number = 0.0
    var unit: String? = null
}

class Value {
    var name: String? = null
    var struct: Struct? = null
    var source: Any? = null
    var id: String? = null
    var path_from_root: List<PathFromRoot>? = null
    var results = 0
}

class Attribute {
    var attribute_group_name: String? = null
    var value_id: String? = null
    var name: String? = null
    var value_name: String? = null
    var value_struct: ValueStruct? = null
    var values: List<Value>? = null
    var attribute_group_id: String? = null
    var source: Any? = null
    var id: String? = null
}

class DifferentialPricing {
    var id = 0
}


class Result {
    var id: String? = null
    var site_id: String? = null
    var title: String? = null
    var seller: Seller? = null
    var price = 0.0
    var prices: Price? = null
    var sale_price: Any? = null
    var currency_id: String? = null
    var available_quantity = 0
    var sold_quantity = 0
    var buying_mode: String? = null
    var listing_type_id: String? = null
    var stop_time: Date? = null
    var condition: String? = null
    var permalink: String? = null
    var thumbnail: String? = null
    var thumbnail_id: String? = null
    var accepts_mercadopago = false
    var installments: Installments? = null
    var address: Address? = null
    var shipping: Shipping? = null
    var seller_address: SellerAddress? = null
    var attributes: List<Attribute>? = null
    var differential_pricing: DifferentialPricing? = null
    var original_price: Any? = null
    var category_id: String? = null
    var official_store_id: Any? = null
    var domain_id: String? = null
    var catalog_product_id: String? = null
    var tags: List<String>? = null
    var catalog_listing = false
    var use_thumbnail_id = false
    var order_backend = 0
}

class Sort {
    var id: String? = null
    var name: String? = null
}

class AvailableSort {
    var id: String? = null
    var name: String? = null
}

class PathFromRoot {
    var id: String? = null
    var name: String? = null
}

class Filter {
    var id: String? = null
    var name: String? = null
    var type: String? = null
    var values: List<Value>? = null
}

class AvailableFilter {
    var id: String? = null
    var name: String? = null
    var type: String? = null
    var values: List<Value>? = null
}

class ItemsQuery {
    var site_id: String? = null
    var query: String? = null
    var paging: Paging? = null
    var results: List<Result>? = null
    var secondary_results: List<Any>? = null
    var related_results: List<Any>? = null
    var sort: Sort? = null
    var available_sorts: List<AvailableSort>? = null
    var filters: List<Filter>? = null
    var available_filters: List<AvailableFilter>? = null
}

