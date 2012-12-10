[:site-title "Bytopia.org"
 :site-description "Inventing, implementing, living a life."
 :site-url "http://bytopia.org"
 :site-author "Alexander Yakushev"
 :date-created 2012
 :in-dir "resources/"
 :out-dir "target/bytopia.org/"
 :default-template "default.clj"
 :encoding "UTF-8"
 :blog-as-index false
 :create-archives false
 :emacs "/usr/bin/emacs"
 :emacs-eval ['(add-to-list 'load-path "~/.emacs.d/elpa/clojure-mode-1.11.5/")
              '(add-to-list 'load-path "~/.emacs.d/elpa/htmlize-1.39/")
              '(require 'htmlize)
              '(require 'org)
              '(require 'ob)
              '(require 'clojure-mode)
              '(global-font-lock-mode 1)
              '(set-face-foreground 'font-lock-string-face "#8abeb7")
              '(set-face-foreground 'font-lock-keyword-face "#b5bd68")
              '(set-face-foreground 'font-lock-function-name-face "#81a2be")
              '(set-face-foreground 'font-lock-builtin-face "#b294bb")
              '(set-face-foreground 'font-lock-comment-face "#969896")]]
