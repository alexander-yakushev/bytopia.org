[:site-title "Bytopia.org"
 :site-description "Inventing, implementing, living a life."
 :site-url "http://www.bytopia.org"
 :site-author "Alexander Yakushev"
 :date-created 2012
 :in-dir "resources/"
 :out-dir "target/www/"
 :default-template "default.clj"
 :encoding "UTF-8"
 :blog-as-index false
 :create-archives false
 :create-tags true
 :rss-for-tags ["clojure"]

 :rsync "rsync -avu -og --chmod=a+rX"
 :host "do-core"
 ;; :user "unlogic"
 :deploy-dir "services/bytopia.org/"
 ;; :post-deploy-cmd ["ssh" "digitalocean-bytopia" "/home/www-data/deploy-www"]

 :emacs "/usr/bin/emacs"
 :emacs-eval [(require 'org) (setq org-export-with-section-numbers nil)]]
