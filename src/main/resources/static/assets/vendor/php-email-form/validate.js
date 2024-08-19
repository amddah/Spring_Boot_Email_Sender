(function () {
  "use strict";

  let forms = document.querySelectorAll('.php-email-form');

  forms.forEach(function (form) {
    form.addEventListener('submit', function (event) {
      event.preventDefault();

      let formData = {
        to: form.querySelector('input[name="to"]').value,
        subject: form.querySelector('input[name="subject"]').value,
        message: form.querySelector('textarea[name="message"]').value
      };

      console.log(formData)


      let action = form.getAttribute('action');
      let recaptcha = form.getAttribute('data-recaptcha-site-key');

      if (!action) {
        displayError(form, 'The form action property is not set!');
        return;
      }

      form.querySelector('.loading').classList.add('d-block');
      form.querySelector('.error-message').classList.remove('d-block');
      form.querySelector('.sent-message').classList.remove('d-block');

      if (recaptcha) {
        if (typeof grecaptcha !== "undefined") {
          grecaptcha.ready(function () {
            try {
              grecaptcha.execute(recaptcha, { action: 'php_email_form_submit' })
                  .then(token => {
                    formData['recaptcha-response'] = token;
                    ajaxSubmitForm(form, action, formData);
                  })
            } catch (error) {
              displayError(form, error);
            }
          });
        } else {
          displayError(form, 'The reCaptcha javascript API url is not loaded!')
        }
      } else {
        console.log(formData)
        ajaxSubmitForm(form, action, formData);
      }
    });
  });

  function ajaxSubmitForm(form, action, formData) {
    $.ajax({
      type: 'POST',
      url: action,
      contentType: 'application/json',
      data: JSON.stringify(formData),
      success: function (data) {
        form.querySelector('.loading').classList.remove('d-block');
        if (data.trim() === 'OK') {
          form.querySelector('.sent-message').classList.add('d-block');
          form.reset();
        } else {
          console.log("this is"+ data)
          throw new Error(data ? data : 'Form submission failed and no error message returned from: ' + action);
        }
      },
      error: function (error) {
        displayError(form, error);
      }
    });
  }

  function displayError(form, error) {
    form.querySelector('.loading').classList.remove('d-block');
    form.querySelector('.error-message').innerHTML = error;
    form.querySelector('.error-message').classList.add('d-block');
  }

})();
