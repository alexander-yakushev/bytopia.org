{:title "Home"
 :skip-title true}

(for [f (take 10 (reverse (static.io/list-files :posts)))
      :let [url (static.core/post-url f)
            [metadata content] (static.io/read-doc f)]]
  [:div {:class "entry-preview"}
   [:h2 {:class "entry-title"}
    [:a {:href url} (:title metadata)]]

   [:div {:class "entry-info"}
    [:span {:class "entry-date"} (:date metadata)]
    (apply conj [:span {:class "entry-tags"} "Tags: "]
           (interpose " "
            (for [tag (.split (:tags metadata) " ")]
              [:a {:href (str "/tags/#" tag)} tag])))
    [:div {:class "clear"}]]

   [:div {:class "entry-content"}
      (if (:synopsis metadata) (first @(:synopsis metadata)) @content)
    [:p {:class "readmore"}
     [:a {:href url} "Read more ->>"]]]])
